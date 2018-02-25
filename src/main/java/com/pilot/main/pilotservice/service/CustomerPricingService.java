package com.pilot.main.pilotservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.pilot.main.pilotrepo.entity.FctDmCompanyLevelActualVsTargetEntity;
import com.pilot.main.pilotrepo.entity.QFctDmCompanyLevelActualVsTargetEntity;
import com.pilot.main.pilotrepo.repo.FctDmCompanyLevelActualVsTargetRepo;
import com.pilot.main.pilotservice.pojo.BetterOf;
import com.pilot.main.pilotservice.pojo.CCC;
import com.pilot.main.pilotservice.pojo.CustomerPricingDetail;
import com.pilot.main.pilotservice.pojo.Funded;
import com.pilot.main.pilotservice.pojo.GrossProfitDollars;
import com.pilot.main.pilotservice.pojo.Margin;
import com.pilot.main.pilotservice.pojo.MixPercentage;
import com.pilot.main.pilotservice.pojo.PFJTotal;
import com.pilot.main.pilotservice.pojo.RetailMinus;
import com.pilot.main.pilotservice.pojo.TotalRetail;
import com.pilot.main.pilotservice.pojo.Volume;

public class CustomerPricingService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPricingService.class);

	@Autowired
	FctDmCompanyLevelActualVsTargetRepo fctDmCompanyLevelActualVsTargetRepo;

	/**
	 * Get details for Customer Pricing screen
	 * 
	 * @return
	 */
	@GetMapping(path = "/getprices")
	public List<CustomerPricingDetail> fetchCustomerPricingDetails() {
		logger.info("---in Customer Pricing Service ---");

		/*
		 * UI data generation for MTD filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> mtdEntities = findEntitiesByTemporalPeriod("MTD");
		CustomerPricingDetail customerPricingDetailMTD = populateCustomerPricingDetail(mtdEntities);

		/*
		 * UI data generation for LCM filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> lcmEntities = findEntitiesByTemporalPeriod("LCM");
		CustomerPricingDetail customerPricingDetailLCM = populateCustomerPricingDetail(lcmEntities);

		/*
		 * UI data generation for LCYTD filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> lcytdEntities = findEntitiesByTemporalPeriod("LCYTD");
		CustomerPricingDetail customerPricingDetailLCYTD = populateCustomerPricingDetail(lcytdEntities);

		List<CustomerPricingDetail> customerPricingDetails = new ArrayList<CustomerPricingDetail>();
		customerPricingDetails.add(customerPricingDetailMTD);
		customerPricingDetails.add(customerPricingDetailLCM);
		customerPricingDetails.add(customerPricingDetailLCYTD);

		return customerPricingDetails;
	}

	private CustomerPricingDetail populateCustomerPricingDetail(List<FctDmCompanyLevelActualVsTargetEntity> fctDmCompanyLevelActualVsTargetEntities) {
		CustomerPricingDetail customerPricingDetail = new CustomerPricingDetail();
		
		/*
		 * MIX_OF_BUSINESS entities
		 */
		FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity = null;
		FctDmCompanyLevelActualVsTargetEntity betterOfEntity = null;
		FctDmCompanyLevelActualVsTargetEntity totalRetailEntity = null;
		FctDmCompanyLevelActualVsTargetEntity reatilMinusEntity = null;
		FctDmCompanyLevelActualVsTargetEntity fundedEntity = null;
		FctDmCompanyLevelActualVsTargetEntity cccEntity = null;
		
		for (FctDmCompanyLevelActualVsTargetEntity fctDmCompanyLevelActualVsTargetEntity : fctDmCompanyLevelActualVsTargetEntities) {
			
			if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("TOTAL")) {
				pfjTotalEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("BETTER OF")) {
				betterOfEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("TOTAL RETAIL")) {
				totalRetailEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("RETAIL MINUS")) {
				reatilMinusEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("FUNDED")) {
				fundedEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getMixOfBusiness().equalsIgnoreCase("CCC")) {
				cccEntity = fctDmCompanyLevelActualVsTargetEntity;
			}
			
			/*
			 * Populate PFJTotal
			 */
			populatePFJTotal(customerPricingDetail, pfjTotalEntity);
			
			/*
			 * Populate BetterOf
			 */
			populateBetterOf(customerPricingDetail, betterOfEntity, pfjTotalEntity);
			
			/*
			 * Populate TotalRetail
			 */
			populateTotalRetail(customerPricingDetail, totalRetailEntity);
			
			/*
			 * Populate RetailMinus
			 */
			populateRetailMinus(customerPricingDetail, reatilMinusEntity);
			
			/*
			 * Populate Funded
			 */
			populateFunded(customerPricingDetail, fundedEntity);
			
			/*
			 * Populate CCC
			 */
			populateCCC(customerPricingDetail, cccEntity);
		}
		return customerPricingDetail;
	}

	private void populatePFJTotal(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(pfjTotalEntity.getActualGrossProfit().subtract(pfjTotalEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(pfjTotalEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(pfjTotalEntity.getActualGrossProfit().subtract(pfjTotalEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(pfjTotalEntity.getActualGrossProfitLy());

		Volume volume = new Volume();
		volume.setVsTgLeft(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getTargetVolume()).divide(pfjTotalEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getActualVolumeLy()).divide(pfjTotalEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(pfjTotalEntity.getActualNetPlMargin().subtract(pfjTotalEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(pfjTotalEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(pfjTotalEntity.getActualNetPlMargin().subtract(pfjTotalEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(pfjTotalEntity.getActualNetPlMarginLy());
		
		PFJTotal pfjTotal = new PFJTotal();
		pfjTotal.setGrossProfitDollars(grossProfitDollars);
		pfjTotal.setVolume(volume);
		pfjTotal.setMargin(margin);
		pfjTotal.setTotalGAL(pfjTotalEntity.getActualVolumeLy());
		pfjTotal.setTotalTarget(pfjTotalEntity.getTargetVolume());
		
		customerPricingDetail.setpFJTotal(pfjTotal);
	}

	private void populateBetterOf(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity betterOfEntity, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(betterOfEntity.getActualGrossProfit().subtract(betterOfEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(betterOfEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(betterOfEntity.getActualGrossProfit().subtract(betterOfEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(betterOfEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(betterOfEntity.getActualVolume().subtract(betterOfEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(betterOfEntity.getActualVolume().subtract(betterOfEntity.getTargetVolume()).divide(betterOfEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(betterOfEntity.getActualVolume().subtract(betterOfEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(betterOfEntity.getActualVolume().subtract(betterOfEntity.getActualVolumeLy()).divide(betterOfEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(betterOfEntity.getActualNetPlMargin().subtract(betterOfEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(betterOfEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(betterOfEntity.getActualNetPlMargin().subtract(betterOfEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(betterOfEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(betterOfEntity.getActualVolume().divide(betterOfEntity.getActualVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(betterOfEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((betterOfEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume()))
									.subtract(betterOfEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy()))
									.divide(betterOfEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy()))
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		BetterOf betterOf = new BetterOf();
		betterOf.setGrossProfitDollars(grossProfitDollars);
		betterOf.setVolume(volume);
		betterOf.setMargin(margin);
		betterOf.setMixPercentage(mixPercentage);
		betterOf.setBuyingPerfActual(betterOfEntity.getActualBuyingPerformance());
		betterOf.setBuyingPerfTarget(betterOfEntity.getTargetBuyingPerformance());
		betterOf.setEffPumpFeeActual(betterOfEntity.getActualEffectivePumpFee());
		betterOf.setEffPumpFeeTarget(betterOfEntity.getTargetEffectivePumpFee());
	}

	private void populateTotalRetail(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity totalRetailEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(totalRetailEntity.getActualGrossProfit().subtract(totalRetailEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(totalRetailEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(totalRetailEntity.getActualGrossProfit().subtract(totalRetailEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(totalRetailEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getTargetVolume()).divide(totalRetailEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getActualVolumeLy()).divide(totalRetailEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(totalRetailEntity.getActualNetPlMargin().subtract(totalRetailEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(totalRetailEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(totalRetailEntity.getActualNetPlMargin().subtract(totalRetailEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(totalRetailEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(totalRetailEntity.getActualVolume().divide(totalRetailEntity.getActualVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(totalRetailEntity.getTargetVolume().divide(totalRetailEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((totalRetailEntity.getActualVolume().divide(totalRetailEntity.getActualVolume()))
									.subtract(totalRetailEntity.getActualVolumeLy().divide(totalRetailEntity.getActualVolumeLy()))
									.divide(totalRetailEntity.getActualVolumeLy().divide(totalRetailEntity.getActualVolumeLy()))
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		TotalRetail totalRetail = new TotalRetail();
		totalRetail.setGrossProfitDollars(grossProfitDollars);
		totalRetail.setVolume(volume);
		totalRetail.setMargin(margin);
		totalRetail.setMixPercentage(mixPercentage);
	}

	private void populateRetailMinus(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity reatilMinusEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(reatilMinusEntity.getActualGrossProfit().subtract(reatilMinusEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(reatilMinusEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(reatilMinusEntity.getActualGrossProfit().subtract(reatilMinusEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(reatilMinusEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(reatilMinusEntity.getActualVolume().subtract(reatilMinusEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(reatilMinusEntity.getActualVolume().subtract(reatilMinusEntity.getTargetVolume()).divide(reatilMinusEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(reatilMinusEntity.getActualVolume().subtract(reatilMinusEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(reatilMinusEntity.getActualVolume().subtract(reatilMinusEntity.getActualVolumeLy()).divide(reatilMinusEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(reatilMinusEntity.getActualNetPlMargin().subtract(reatilMinusEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(reatilMinusEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(reatilMinusEntity.getActualNetPlMargin().subtract(reatilMinusEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(reatilMinusEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(reatilMinusEntity.getActualVolume().divide(reatilMinusEntity.getActualVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(reatilMinusEntity.getTargetVolume().divide(reatilMinusEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((reatilMinusEntity.getActualVolume().divide(reatilMinusEntity.getActualVolume()))
									.subtract(reatilMinusEntity.getActualVolumeLy().divide(reatilMinusEntity.getActualVolumeLy()))
									.divide(reatilMinusEntity.getActualVolumeLy().divide(reatilMinusEntity.getActualVolumeLy()))
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		RetailMinus retailMinus = new RetailMinus();
		retailMinus.setGrossProfitDollars(grossProfitDollars);
		retailMinus.setVolume(volume);
		retailMinus.setMargin(margin);
		retailMinus.setMixPercentage(mixPercentage);
		//TODO Populate data from table
		retailMinus.setRmDiscountActual(BigDecimal.valueOf(0));
		retailMinus.setRmDiscountTarget(BigDecimal.valueOf(0));
	}

	private void populateFunded(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity fundedEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(fundedEntity.getActualGrossProfit().subtract(fundedEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(fundedEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(fundedEntity.getActualGrossProfit().subtract(fundedEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(fundedEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(fundedEntity.getActualVolume().subtract(fundedEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(fundedEntity.getActualVolume().subtract(fundedEntity.getTargetVolume()).divide(fundedEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(fundedEntity.getActualVolume().subtract(fundedEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(fundedEntity.getActualVolume().subtract(fundedEntity.getActualVolumeLy()).divide(fundedEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(fundedEntity.getActualNetPlMargin().subtract(fundedEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(fundedEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(fundedEntity.getActualNetPlMargin().subtract(fundedEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(fundedEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(fundedEntity.getActualVolume().divide(fundedEntity.getActualVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(fundedEntity.getTargetVolume().divide(fundedEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((fundedEntity.getActualVolume().divide(fundedEntity.getActualVolume()))
									.subtract(fundedEntity.getActualVolumeLy().divide(fundedEntity.getActualVolumeLy()))
									.divide(fundedEntity.getActualVolumeLy().divide(fundedEntity.getActualVolumeLy()))
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		Funded funded = new Funded();
		funded.setGrossProfitDollars(grossProfitDollars);
		funded.setVolume(volume);
		funded.setMargin(margin);
		funded.setMixPercentage(mixPercentage);
	}

	private void populateCCC(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity cccEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(cccEntity.getActualGrossProfit().subtract(cccEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(cccEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(cccEntity.getActualGrossProfit().subtract(cccEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(cccEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(cccEntity.getActualVolume().subtract(cccEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(cccEntity.getActualVolume().subtract(cccEntity.getTargetVolume()).divide(cccEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(cccEntity.getActualVolume().subtract(cccEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(cccEntity.getActualVolume().subtract(cccEntity.getActualVolumeLy()).divide(cccEntity.getActualVolumeLy()).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(cccEntity.getActualNetPlMargin().subtract(cccEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(cccEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(cccEntity.getActualNetPlMargin().subtract(cccEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(cccEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(cccEntity.getActualVolume().divide(cccEntity.getActualVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(cccEntity.getTargetVolume().divide(cccEntity.getTargetVolume()).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((cccEntity.getActualVolume().divide(cccEntity.getActualVolume()))
									.subtract(cccEntity.getActualVolumeLy().divide(cccEntity.getActualVolumeLy()))
									.divide(cccEntity.getActualVolumeLy().divide(cccEntity.getActualVolumeLy()))
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		CCC ccc = new CCC();
		ccc.setGrossProfitDollars(grossProfitDollars);
		ccc.setVolume(volume);
		ccc.setMargin(margin);
		ccc.setMixPercentage(mixPercentage);
	}

	private List<FctDmCompanyLevelActualVsTargetEntity> findEntitiesByTemporalPeriod(String temporalPeriod) {
		return (List<FctDmCompanyLevelActualVsTargetEntity>) fctDmCompanyLevelActualVsTargetRepo
				.findAll(QFctDmCompanyLevelActualVsTargetEntity.fctDmCompanyLevelActualVsTargetEntity.temporalPeriod.eq(temporalPeriod));
	}
}