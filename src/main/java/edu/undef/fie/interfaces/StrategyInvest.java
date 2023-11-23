package edu.undef.fie.interfaces;

import edu.undef.fie.domain.CriptoCurrency;
import edu.undef.fie.domain.Investor;


public interface StrategyInvest {
    void downInvest(Investor investor, CriptoCurrency criptoCurrency);
    void highInvest(Investor investor, CriptoCurrency criptoCurrency);
}
