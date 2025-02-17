package org.example.pages;

public enum SocialLinks {
    TWITTER(1, "https://twitter.com/saucelabs", "https://x.com/saucelabs?mx=2"),
    FACEBOOK(2, "https://www.facebook.com/saucelabs", "https://www.facebook.com/saucelabs"),
    LINKEDIN(3, "https://www.linkedin.com/company/sauce-labs/", "https://www.linkedin.com/company/sauce-labs/");

    private final int linkIndex;
    private final String url;
    private final String redirectUrl;

    SocialLinks(int linkIndex, String url, String redirectUrl) {
        this.linkIndex = linkIndex;
        this.url = url;
        this.redirectUrl = redirectUrl;
    }

    public String getUrl() {
        return url;
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
