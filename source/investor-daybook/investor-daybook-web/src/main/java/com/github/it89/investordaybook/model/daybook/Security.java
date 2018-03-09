package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "security")
public class Security {
    @Nullable
    private Long id;
    private int version;
    private String isin;
    private String ticker;
    private String caption;
    @Nullable
    private String codeGRN;
    private AppUser appUser;

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NotNull
    @Column(name = "isin", nullable = false)
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @NotNull
    @Column(name = "ticker", nullable = false)
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @NotNull
    @Column(name = "caption", nullable = false)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NotNull
    @Column(name = "code_grn")
    public String getCodeGRN() {
        return codeGRN;
    }

    public void setCodeGRN(@Nullable String codeGRN) {
        this.codeGRN = codeGRN;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "app_user_id")
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("appUser must be specified");
        }
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", isin='" + isin + '\'' +
                ", ticker='" + ticker + '\'' +
                ", caption='" + caption + '\'' +
                ", codeGRN='" + codeGRN + '\'' +
                ", appUser=" + appUser +
                '}';
    }
}
