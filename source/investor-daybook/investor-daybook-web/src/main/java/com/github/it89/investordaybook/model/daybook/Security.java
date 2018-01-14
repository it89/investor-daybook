package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;
import org.jetbrains.annotations.Nullable;

public abstract class Security {
    @Nullable
    protected Long id;
    protected String isin;
    protected SecurityType type;
    protected String ticker;
    protected String caption;
    @Nullable
    protected String codeGRN;
    protected AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        if ((isin == null) || isin.length() == 0) {
            throw new IllegalArgumentException("isin must be specified");
        }
        this.isin = isin;
    }

    public SecurityType getType() {
        return type;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        if ((ticker == null) || ticker.length() == 0) {
            throw new IllegalArgumentException("ticker must be specified");
        }
        this.ticker = ticker;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        if ((caption == null) || caption.length() == 0) {
            throw new IllegalArgumentException("caption must be specified");
        }
        this.caption = caption;
    }

    public String getCodeGRN() {
        return codeGRN;
    }

    public void setCodeGRN(String codeGRN) {
        this.codeGRN = codeGRN;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("appUser must be specified");
        }
        this.appUser = appUser;
    }
}
