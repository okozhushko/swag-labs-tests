package org.example.pages;

public enum SocialLinks {

    TWITTER("https://twitter.com/saucelabs", "https://x.com/saucelabs?mx=2"),
    FACEBOOK("https://www.facebook.com/saucelabs", "https://www.facebook.com/saucelabs"),
    LINKEDIN("https://www.linkedin.com/company/sauce-labs/", "https://www.linkedin.com/company/sauce-labs/");

    private final String iconUrl;
    private final String redirectUrl;

    SocialLinks(String iconUrl, String redirectUrl) {
        this.iconUrl = iconUrl;
        this.redirectUrl = redirectUrl;
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
