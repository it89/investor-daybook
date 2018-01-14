package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

public class SecurityBond extends Security {
    private SecurityBond() {
        type = SecurityType.BOND;
    }

    public static class Builder {
        private Long id;
        private final String isin;
        private String ticker;
        private String caption;
        private String codeGRN;
        private AppUser appUser;

        public Builder(String isin) {
            this.isin = isin;
        }

        public Builder id(Long id) {
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

        public SecurityBond build() {
            SecurityBond security = new SecurityBond();
            security.setId(this.id);
            security.setIsin(this.isin);
            security.setTicker(this.ticker);
            security.setCaption(this.caption);
            security.setCodeGRN(this.codeGRN);
            security.setAppUser(this.appUser);
            return security;
        }
    }

    @Override
    public String toString() {
        return "SecurityBond{" +
                "id=" + id +
                ", isin='" + isin + '\'' +
                ", type=" + type +
                ", ticker='" + ticker + '\'' +
                ", caption='" + caption + '\'' +
                ", codeGRN='" + codeGRN + '\'' +
                ", appUser=" + appUser +
                '}';
    }
}
