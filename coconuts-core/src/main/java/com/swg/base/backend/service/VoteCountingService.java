package com.swg.base.backend.service;

import com.swg.base.backend.entity.VoteResult;

import java.util.Date;
import java.util.List;

/**
 * Kontrak untuk service perhitungan jumlah/perolehan suara dari masing-masing kandidat.
 *
 * @author zakyalvan
 */
public interface VoteCountingService {
    /**
     * Masukin data hasil perhitungan atau perolehan suara baru.
     *
     * @param voteResult
     */
    void addVoteResult(VoteResult voteResult);

    /**
     * Retrieve seluruh hasil perhitungan perolehan suara dari seluruh kandidat.
     *
     * @return {@link List<VoteResult>}
     */
    List<VoteResult> getVoteResults();

    /**
     * Retrieve hasil perhitungan perolehan suara dari seluruh kandidat mulai version
     * date tertentu.
     *
     * @param versionStart
     * @return
     */
    List<VoteResult> getVoteResults(Date versionStart);

    /**
     * Retrieve hasil perhitungan perolehan suara dari seluruh kandidat mulao version
     * date tertentu samapi dengan version date tertentu pula.
     *
     * @param versionStart
     * @param versionEnd
     * @return
     */
    List<VoteResult> getVoteResults(Date versionStart, Date versionEnd);
}
