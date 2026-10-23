package org.example.pages;

public enum SocialLinks {

    TWITTER("X", "https://x.com/saucelabs", "https://x.com/saucelabs"),
    FACEBOOK("Facebook", "https://www.facebook.com/saucelabs", "https://www.facebook.com/saucelabs"),
    LINKEDIN("LinkedIn", "https://www.linkedin.com/company/sauce-labs/", "https://www.linkedin.com/company/sauce-labs/");

    private final String linkText;
    private final String iconUrl;
    private final String redirectUrl;

    SocialLinks(String linkText, String iconUrl, String redirectUrl) {
        this.linkText = linkText;
        this.iconUrl = iconUrl;
        this.redirectUrl = redirectUrl;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public static SocialLinks fromString(String socialLink) {
        return switch (socialLink.toUpperCase()) {
            case "TWITTER" -> TWITTER;
            case "FACEBOOK" -> FACEBOOK;
            case "LINKEDIN" -> LINKEDIN;
            default -> throw new IllegalArgumentException("Unexpected value: " + socialLink);
        };
    }
}
