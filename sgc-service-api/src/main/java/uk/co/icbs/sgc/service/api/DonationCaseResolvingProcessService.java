package uk.co.icbs.sgc.service.api;

import uk.co.icbs.common.service.model.AbstractService;
import uk.co.icbs.sgc.service.model.DonationCaseResolvingProcess;

public interface DonationCaseResolvingProcessService extends AbstractService<DonationCaseResolvingProcess> {
    ResponseModel<DonationCaseResolvingProcess> findAllByDonationCaseId(String donationCaseId, int page, int size);
}
