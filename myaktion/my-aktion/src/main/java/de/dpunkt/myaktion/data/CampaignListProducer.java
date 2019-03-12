package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;

@SessionScoped
@Named
public class CampaignListProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335819936144510848L;
	
	private List<Campaign> campaigns;
	
	public CampaignListProducer() {
		campaigns = createMockCampaigns();
	}
	
	private List<Campaign> createMockCampaigns() {
		Donation donation = new Donation();
		donation.setDonorName("Heinz Schmidt");
		donation.setAmount(20d);
		donation.setReceiptRequested(true);
		donation.setStatus(Status.TRANSFERRED);
		donation.setAccount(new Account(donation.getDonorName(), "XXX BANK", "DE44876543210000123456"));
		Donation donation2 = new Donation();
		donation2.setDonorName("Karl Meier");
		donation2.setAmount(30d);
		donation2.setReceiptRequested(false);
		donation2.setStatus(Status.IN_PROCESS);
		donation2.setAccount(new Account(donation2.getDonorName(), "YYY BANK", "DE44864275310000654321"));
		List<Donation> spenden = new LinkedList<>();
		spenden.add(donation);
		spenden.add(donation2);
		Campaign campaign = new Campaign();
		campaign.setName("Trikots für A-Jugend");
		campaign.setTargetAmount(1000d);
		campaign.setAmountDonatedSoFar(258d);
		campaign.setDonationMinimum(20d);
		campaign.setId(1L);
		campaign.setAccount(new Account("Max Mustermann","ABC Bank","DE44123456780100200300"));
		campaign.setDonations(spenden);
		Campaign campaign2 = new Campaign();
		campaign2.setName("Rollstuhl für Maria");
		campaign2.setTargetAmount(2500d);
		campaign2.setAmountDonatedSoFar(742d);
		campaign2.setDonationMinimum(25d);
		campaign2.setId(2L);
		campaign2.setAccount(campaign.getAccount());
		campaign2.setDonations(spenden);
		List<Campaign> ret = new LinkedList<>();
		ret.add(campaign);
		ret.add(campaign2);
		return ret;
		
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	

}
