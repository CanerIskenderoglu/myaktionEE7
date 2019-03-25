package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;

@Stateless
public class DonationServiceBean implements DonationService {
	
	@Inject
	private EntityManager entityManger;

	@Override
	public List<Donation> getDonationList(Long campaignID) {
		Campaign managedCampaign = entityManger.find(Campaign.class, campaignID);
		List<Donation> donations = managedCampaign.getDonations();
		donations.size();
		return donations;
	}

	@Override
	public void addDonation(Long campaignID, Donation donation) {
		Campaign managedCampaign = entityManger.find(Campaign.class, campaignID);
		donation.setCampaign(managedCampaign);
		entityManger.persist(donation);
	}
	
	

}
