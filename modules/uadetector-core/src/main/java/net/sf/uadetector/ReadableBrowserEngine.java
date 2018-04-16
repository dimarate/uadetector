package net.sf.uadetector;

import javax.annotation.Nonnull;

public interface ReadableBrowserEngine {

	/**
	 * Gets the family of an browser engine.
	 *
	 * @return family of an browser engine
	 */
	@Nonnull
	BrowserEngineFamily getFamily();

	/**
	 * Returns the URL to the product or information page of an browser engine.
	 *
	 * @return the URL to the product page
	 */
	@Nonnull
	String getInfoUrl();

	/**
	 * Gets the version number of an browser engine.
	 *
	 * @return version number of an browser engine
	 */
	@Nonnull
	VersionNumber getVersionNumber();

}
