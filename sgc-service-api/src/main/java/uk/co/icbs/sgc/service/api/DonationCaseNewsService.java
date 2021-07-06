package uk.co.icbs.sgc.service.api;

import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCaseNews;

public interface DonationCaseNewsService extends AbstractService<DonationCaseNews> {
    ResponseModel<DonationCaseNews> findAllByDonationCaseId(String donationCaseId, int page, int size);
    ResponseModel<DonationCaseNews> findAllByDonatorId(String donatorId, int page, int size);
    ResponseModel<DonationCaseNews> findAllByRecipientId(String recipientId, int page, int size);
}
