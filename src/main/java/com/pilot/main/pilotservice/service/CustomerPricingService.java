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
import com.pilot.main.pilotservice.pojo.CustomerPricingDetail;
import com.pilot.main.pilotservice.pojo.GrossProfitDollars;
import com.pilot.main.pilotservice.pojo.Margin;
import com.pilot.main.pilotservice.pojo.MixPercentage;
import com.pilot.main.pilotservice.pojo.PFJTotal;
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

	private void populateTotalRetail(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity customerPricingEntity) {
		// TODO Auto-generated method stub
		
	}

	private void populateRetailMinus(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity customerPricingEntity) {
		// TODO Auto-generated method stub
		
	}

	private void populateFunded(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity customerPricingEntity) {
		// TODO Auto-generated method stub
		
	}

	private void populateCCC(CustomerPricingDetail customerPricingDetail, FctDmCompanyLevelActualVsTargetEntity customerPricingEntity) {
		// TODO Auto-generated method stub
		
	}

	private List<FctDmCompanyLevelActualVsTargetEntity> findEntitiesByTemporalPeriod(String temporalPeriod) {
		return (List<FctDmCompanyLevelActualVsTargetEntity>) fctDmCompanyLevelActualVsTargetRepo
				.findAll(QFctDmCompanyLevelActualVsTargetEntity.fctDmCompanyLevelActualVsTargetEntity.temporalPeriod.eq(temporalPeriod));
	}
}