package com.pilot.main.pilotservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class CustomerPricingService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPricingService.class);

	@Autowired
	FctDmCompanyLevelActualVsTargetRepo fctDmCompanyLevelActualVsTargetRepo;

	public List<CustomerPricingDetail> fetchCustomerPricingDetails() {
		logger.info("---in Customer Pricing Service ---");

		/*
		 * UI data generation for MTD filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> mtdEntities = findEntitiesByTemporalPeriod("MTD");
		logger.info("Found MTD type rows ---> " + mtdEntities.size());
		CustomerPricingDetail customerPricingDetailMTD = populateCustomerPricingDetail(mtdEntities);
		customerPricingDetailMTD.setTemporalPeriod("MTD");
		// customerPricingDetailMTD.setLastClosedPeriod(mtdEntities.get(0).getLastClosedPeriod());

		/*
		 * UI data generation for LCM filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> lcmEntities = findEntitiesByTemporalPeriod("LCM");
		logger.info("Found LCM type rows ---> " + lcmEntities.size());
		CustomerPricingDetail customerPricingDetailLCM = populateCustomerPricingDetail(lcmEntities);
		customerPricingDetailLCM.setTemporalPeriod("LCM");
		// customerPricingDetailLCM.setLastClosedPeriod(lcmEntities.get(0).getLastClosedPeriod());

		/*
		 * UI data generation for LCYTD filter
		 */
		List<FctDmCompanyLevelActualVsTargetEntity> lcytdEntities = findEntitiesByTemporalPeriod("LCYTD");
		logger.info("Found LCYTD type rows ---> " + lcytdEntities.size());
		CustomerPricingDetail customerPricingDetailLCYTD = populateCustomerPricingDetail(lcytdEntities);
		customerPricingDetailLCYTD.setTemporalPeriod("LCYTD");
		// customerPricingDetailLCYTD.setLastClosedPeriod(lcytdEntities.get(0).getLastClosedPeriod());

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
		FctDmCompanyLevelActualVsTargetEntity retailMinusEntity = null;
		FctDmCompanyLevelActualVsTargetEntity fundedEntity = null;
		FctDmCompanyLevelActualVsTargetEntity cccEntity = null;
		
		for (FctDmCompanyLevelActualVsTargetEntity fctDmCompanyLevelActualVsTargetEntity : fctDmCompanyLevelActualVsTargetEntities) {
			
			if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("TOTAL")) {
				pfjTotalEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("BETTER OF")) {
				betterOfEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("TOTAL RETAIL")) {
				totalRetailEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("RETAIL MINUS")) {
				retailMinusEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("FUNDED")) {
				fundedEntity = fctDmCompanyLevelActualVsTargetEntity;
			} else if (fctDmCompanyLevelActualVsTargetEntity.getFctDmCompanyLevelActualVsTargetId().getMixOfBusiness().equalsIgnoreCase("CCC")) {
				cccEntity = fctDmCompanyLevelActualVsTargetEntity;
			}
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
		populateTotalRetail(customerPricingDetail, totalRetailEntity, pfjTotalEntity);
		
		/*
		 * Populate RetailMinus
		 */
		populateRetailMinus(customerPricingDetail, retailMinusEntity, pfjTotalEntity);
		
		/*
		 * Populate Funded
		 */
		populateFunded(customerPricingDetail, fundedEntity, pfjTotalEntity);
		
		/*
		 * Populate CCC
		 */
		populateCCC(customerPricingDetail, cccEntity, pfjTotalEntity);
		
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
		volume.setVsTgRight(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getTargetVolume()).divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(pfjTotalEntity.getActualVolume().subtract(pfjTotalEntity.getActualVolumeLy()).divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
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
		volume.setVsTgRight(betterOfEntity.getActualVolume().subtract(betterOfEntity.getTargetVolume()).divide(betterOfEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(betterOfEntity.getActualVolume().subtract(betterOfEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(betterOfEntity.getActualVolume().subtract(betterOfEntity.getActualVolumeLy()).divide(betterOfEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(betterOfEntity.getActualNetPlMargin().subtract(betterOfEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(betterOfEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(betterOfEntity.getActualNetPlMargin().subtract(betterOfEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(betterOfEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(betterOfEntity.getActualVolume().divide(betterOfEntity.getActualVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(betterOfEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((betterOfEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume(), 6, RoundingMode.HALF_UP))
									.subtract(betterOfEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP))
									.divide(betterOfEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP), 6, RoundingMode.HALF_UP)
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
		
		customerPricingDetail.setBetterOf(betterOf);
	}

	private void populateTotalRetail(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity totalRetailEntity, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
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
		volume.setVsTgRight(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getTargetVolume()).divide(totalRetailEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(totalRetailEntity.getActualVolume().subtract(totalRetailEntity.getActualVolumeLy()).divide(totalRetailEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(totalRetailEntity.getActualNetPlMargin().subtract(totalRetailEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(totalRetailEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(totalRetailEntity.getActualNetPlMargin().subtract(totalRetailEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(totalRetailEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(totalRetailEntity.getActualVolume().divide(totalRetailEntity.getActualVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(totalRetailEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((totalRetailEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume(), 6, RoundingMode.HALF_UP))
									.subtract(totalRetailEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP))
									.divide(totalRetailEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP), 6, RoundingMode.HALF_UP)
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		TotalRetail totalRetail = new TotalRetail();
		totalRetail.setGrossProfitDollars(grossProfitDollars);
		totalRetail.setVolume(volume);
		totalRetail.setMargin(margin);
		totalRetail.setMixPercentage(mixPercentage);
		
		customerPricingDetail.setTotalRetail(totalRetail);
	}

	private void populateRetailMinus(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity retailMinusEntity, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
		GrossProfitDollars grossProfitDollars = new GrossProfitDollars();
		grossProfitDollars.setVsTgLeft(retailMinusEntity.getActualGrossProfit().subtract(retailMinusEntity.getTargetGrossProfit()));
		grossProfitDollars.setVsTgLeftPositive(grossProfitDollars.getVsTgLeft().signum() > 0);
		grossProfitDollars.setVsTgRight(retailMinusEntity.getTargetGrossProfit());
		grossProfitDollars.setVsLyLeft(retailMinusEntity.getActualGrossProfit().subtract(retailMinusEntity.getActualGrossProfitLy()));
		grossProfitDollars.setVsLyLeftPositive(grossProfitDollars.getVsLyLeft().signum() > 0);
		grossProfitDollars.setVsLyRight(retailMinusEntity.getActualGrossProfitLy());
		
		Volume volume = new Volume();
		volume.setVsTgLeft(retailMinusEntity.getActualVolume().subtract(retailMinusEntity.getTargetVolume()));
		volume.setVsTgLeftPositive(volume.getVsTgLeft().signum() > 0);
		volume.setVsTgRight(retailMinusEntity.getActualVolume().subtract(retailMinusEntity.getTargetVolume()).divide(retailMinusEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(retailMinusEntity.getActualVolume().subtract(retailMinusEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(retailMinusEntity.getActualVolume().subtract(retailMinusEntity.getActualVolumeLy()).divide(retailMinusEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(retailMinusEntity.getActualNetPlMargin().subtract(retailMinusEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(retailMinusEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(retailMinusEntity.getActualNetPlMargin().subtract(retailMinusEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(retailMinusEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(retailMinusEntity.getActualVolume().divide(retailMinusEntity.getActualVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(retailMinusEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((retailMinusEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume(), 6, RoundingMode.HALF_UP))
									.subtract(retailMinusEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP))
									.divide(retailMinusEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP), 6, RoundingMode.HALF_UP)
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
		
		customerPricingDetail.setRetailMinus(retailMinus);
	}

	private void populateFunded(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity fundedEntity, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
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
		volume.setVsTgRight(fundedEntity.getActualVolume().subtract(fundedEntity.getTargetVolume()).divide(fundedEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(fundedEntity.getActualVolume().subtract(fundedEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(fundedEntity.getActualVolume().subtract(fundedEntity.getActualVolumeLy()).divide(fundedEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(fundedEntity.getActualNetPlMargin().subtract(fundedEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(fundedEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(fundedEntity.getActualNetPlMargin().subtract(fundedEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(fundedEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(fundedEntity.getActualVolume().divide(fundedEntity.getActualVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(fundedEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((fundedEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume(), 6, RoundingMode.HALF_UP))
									.subtract(fundedEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP))
									.divide(fundedEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP), 6, RoundingMode.HALF_UP)
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		Funded funded = new Funded();
		funded.setGrossProfitDollars(grossProfitDollars);
		funded.setVolume(volume);
		funded.setMargin(margin);
		funded.setMixPercentage(mixPercentage);
		
		customerPricingDetail.setFunded(funded);
	}

	private void populateCCC(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity cccEntity, FctDmCompanyLevelActualVsTargetEntity pfjTotalEntity) {
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
		volume.setVsTgRight(cccEntity.getActualVolume().subtract(cccEntity.getTargetVolume()).divide(cccEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		volume.setVsLyLeft(cccEntity.getActualVolume().subtract(cccEntity.getActualVolumeLy()));
		volume.setVsLyLeftPositive(volume.getVsLyLeft().signum() > 0);
		volume.setVsLyRight(cccEntity.getActualVolume().subtract(cccEntity.getActualVolumeLy()).divide(cccEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		
		Margin margin = new Margin();
		margin.setVsTgLeft(cccEntity.getActualNetPlMargin().subtract(cccEntity.getTargetNetPlMargin()));
		margin.setVsTgLeftPositive(margin.getVsTgLeft().signum() > 0);
		margin.setVsTgRight(cccEntity.getTargetNetPlMargin());
		margin.setVsLyLeft(cccEntity.getActualNetPlMargin().subtract(cccEntity.getActualNetPlMarginLy()));
		margin.setVsLyLeftPositive(margin.getVsLyLeft().signum() > 0);
		margin.setVsLyRight(cccEntity.getActualNetPlMarginLy());
		
		MixPercentage mixPercentage = new MixPercentage();
		mixPercentage.setMixActual(cccEntity.getActualVolume().divide(cccEntity.getActualVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixTarget(cccEntity.getTargetVolume().divide(pfjTotalEntity.getTargetVolume(), 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLy((cccEntity.getActualVolume().divide(pfjTotalEntity.getActualVolume(), 6, RoundingMode.HALF_UP))
									.subtract(cccEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP))
									.divide(cccEntity.getActualVolumeLy().divide(pfjTotalEntity.getActualVolumeLy(), 6, RoundingMode.HALF_UP), 6, RoundingMode.HALF_UP)
									.multiply(BigDecimal.valueOf(100)));
		mixPercentage.setMixVsLyPositive(mixPercentage.getMixVsLy().signum() > 0);
		
		CCC ccc = new CCC();
		ccc.setGrossProfitDollars(grossProfitDollars);
		ccc.setVolume(volume);
		ccc.setMargin(margin);
		ccc.setMixPercentage(mixPercentage);
		
		customerPricingDetail.setCcc(ccc);
	}

	private List<FctDmCompanyLevelActualVsTargetEntity> findEntitiesByTemporalPeriod(String temporalPeriod) {
		return (List<FctDmCompanyLevelActualVsTargetEntity>) fctDmCompanyLevelActualVsTargetRepo
				.findAll(QFctDmCompanyLevelActualVsTargetEntity.fctDmCompanyLevelActualVsTargetEntity.fctDmCompanyLevelActualVsTargetId.temporalPeriod.eq(temporalPeriod));
	}
}