package net.sf.uadetector;

import java.io.Serializable;

import javax.annotation.Nonnull;

import net.sf.qualitycheck.Check;

/**
 * {@code BrowserEngine} is an immutable entity that represents the informations about an browser engine like WebKit,
 * Trident or Presto.
 * 
 * @author Dmitri Bedunkevits
 */
public final class BrowserEngine implements ReadableBrowserEngine, Serializable {

	public static final BrowserEngine UNKNOWN = new BrowserEngine(BrowserEngineFamily.UNKNOWN, "", VersionNumber.UNKNOWN);

	/**
	 * Serialization version
	 */
	private static final long serialVersionUID = 1L;

	@Nonnull
	private final BrowserEngineFamily family;

	@Nonnull
	private final String infoUrl;

	@Nonnull
	private final VersionNumber versionNumber;

	public BrowserEngine(@Nonnull final BrowserEngineFamily family, @Nonnull final String url, @Nonnull final VersionNumber versionNumber) {
		Check.notNull(family, "family");
		Check.notNull(url, "infoUrl");
		Check.notNull(versionNumber, "versionNumber");

		this.family = family;
		this.infoUrl = url;
		this.versionNumber = versionNumber;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BrowserEngine other = (BrowserEngine) obj;

		if (!family.equals(other.family)) {
			return false;
		}
		if (!infoUrl.equals(other.infoUrl)) {
			return false;
		}
		if (!versionNumber.equals(other.versionNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public BrowserEngineFamily getFamily() {
		return family;
	}

	@Override
	public String getInfoUrl() {
		return infoUrl;
	}

	@Override
	public VersionNumber getVersionNumber() {
		return versionNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + family.hashCode();
		result = prime * result + infoUrl.hashCode();
		result = prime * result + versionNumber.hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(", family=");
		builder.append(family);
		builder.append(", infoUrl=");
		builder.append(infoUrl);
		builder.append(", versionNumber=");
		builder.append(versionNumber);
		builder.append("]");
		return builder.toString();
	}

}
