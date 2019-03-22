package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Deleted;
import de.dpunkt.myaktion.util.Events.Updated;

@SessionScoped
public class CampaignListProducer implements Serializable {

	@Inject
	private CampaignService campaignService;

	private List<Campaign> campaigns;

	@PostConstruct
	public void init() {
		campaigns = campaignService.getAllCampaigns();
	}

	@Produces
	@Named
	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void onCampaignAdded(@Observes @Added Campaign campaign) {
		campaignService.addCampaign(campaign);
		init();
	}

	public void onCampaignDeleted(@Observes @Deleted Campaign campaign) {
		campaignService.deleteCampaign(campaign);
		init();
	}

	public void onCampaignUpdated(@Observes @Updated Campaign campaign) {
		campaignService.updateCampaign(campaign);
		init();
	}



}
