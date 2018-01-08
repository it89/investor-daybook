package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

public class SecurityStock extends Security {
    private SecurityStock() {
        type = SecurityType.STOCK;
    }

    public static class Builder {
        private long id;
        private final String isin;
        private String ticker;
        private String caption;
        private String codeGRN;
        private AppUser appUser;

        public Builder(String isin) {
            this.isin = isin;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder ticker(String ticker) {
            this.ticker = ticker;
            return this;
        }

        public Builder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public Builder codeGRN(String codeGRN) {
            this.codeGRN = codeGRN;
            return this;
        }

        public Builder appUser(AppUser appUser) {
            this.appUser = appUser;
            return this;
        }

        public SecurityStock build() {
            SecurityStock security = new SecurityStock();
            security.setId(this.id);
            security.setIsin(this.isin);
            security.setTicker(this.ticker);
            security.setCaption(this.caption);
            security.setCodeGRN(this.codeGRN);
            security.setAppUser(this.appUser);
            return security;
        }
    }



}
