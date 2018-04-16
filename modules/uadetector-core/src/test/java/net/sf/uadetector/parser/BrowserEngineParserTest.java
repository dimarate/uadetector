/*******************************************************************************
 * Copyright 2012 André Rouél
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sf.uadetector.parser;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import net.sf.qualitycheck.exception.IllegalNullArgumentException;
import net.sf.uadetector.BrowserEngine;
import net.sf.uadetector.BrowserEngineFamily;
import net.sf.uadetector.DeviceCategory;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.OperatingSystemFamily;
import net.sf.uadetector.ReadableDeviceCategory.Category;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentFamily;
import net.sf.uadetector.UserAgentType;
import net.sf.uadetector.VersionNumber;
import net.sf.uadetector.datastore.DataStore;
import net.sf.uadetector.datastore.RefreshableDataStore;
import net.sf.uadetector.datastore.TestXmlDataStore;
import net.sf.uadetector.internal.data.domain.Robot;

public class BrowserEngineParserTest {

	private static final RefreshableDataStore DATA_STORE = new TestXmlDataStore();

	private static final UserAgentStringParserImpl<DataStore> PARSER = new UserAgentStringParserImpl<DataStore>(DATA_STORE);

	// refresh data store
	static {
		DATA_STORE.refresh();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void construct_stream_null() throws Exception {
		new UserAgentStringParserImpl<DataStore>(null);
	}

	@Test
	public void getCurrentVersion() {
		assertThat(PARSER.getDataStore().getData().getVersion()).isEqualTo(TestXmlDataStore.VERSION_NEWER);
		assertThat(PARSER.getDataVersion()).isEqualTo(TestXmlDataStore.VERSION_NEWER);
	}

	@Test
	public void getDataStore() {
		assertThat(PARSER.getDataStore()).isNotNull();
		assertThat(PARSER.getDataStore().getData()).isNotNull();
	}

	@Test
	public void parse_engine_Blink_browser_Chrome_60() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Blink_browser_Chrome_44() throws Exception {
		final String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Blink_browser_Opera_48() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36 OPR/48.0.2685.52";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Blink_browser_Cordova_Android() throws Exception {
		final String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; LG-D686 Build/KOT49I.D68620d) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 cordova/android";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Blink_browser_Android_Native() throws Exception {
		final String userAgent = "Mozilla/5.0 (Linux; U; Android 4.4.2; ru-ru; LG-D686 Build/KOT49I.D68620d) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.1599.103 Mobile Safari/537.36";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Blink_browser_Chrome_64() throws Exception {
		final String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2; LG-D686 Build/KOT49I.D68620d) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.137 Mobile Safari/537.36";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.BLINK, "", VersionNumber.parseVersion("537.36"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_WebKit_browser_Safari_9_1() throws Exception {
		final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.WEBKIT, "", VersionNumber.parseVersion("601.7.7"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_WebKit_browser_Chrome_26() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.WEBKIT, "", VersionNumber.parseVersion("537.31"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_WebKit_browser_Android_4() throws Exception {
		final String userAgent = "Mozilla/5.0 (Linux; U; Android 4.0.4; pt-br; MZ608 Build/7.7.1-141-7-FLEM-UMTS-LA) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.WEBKIT, "", VersionNumber.parseVersion("534.30"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_WebKit_browser_Cordova_iOS() throws Exception {
		final String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_5 like Mac OS X) AppleWebKit/604.5.6 (KHTML, like Gecko) Mobile/15D60 cordova/ios";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.WEBKIT, "", VersionNumber.parseVersion("604.5.6"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Trident_browser_IE_11() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.TRIDENT, "", VersionNumber.parseVersion("7.0"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Trident_browser_IE_9() throws Exception {
		final String userAgent = "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1)";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.TRIDENT, "", VersionNumber.UNKNOWN);
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Trident_browser_IE_6() throws Exception {
		final String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.TRIDENT, "", VersionNumber.UNKNOWN);
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Trident_browser_IE_11_mobile() throws Exception {
		final String userAgent = "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 640 LTE) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.TRIDENT, "", VersionNumber.parseVersion("7.0"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Presto_browser_Opera_12() throws Exception {
		final String userAgent = "Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.PRESTO, "", VersionNumber.parseVersion("2.12.388"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Presto_browser_Opera_Mini_5_1() throws Exception {
		final String userAgent = "Opera/9.80 (J2ME/MIDP; Opera Mini/5.1.21219/19.999; en-US; rv:1.9.3a5) WebKit/534.5 Presto/2.6.30";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.PRESTO, "", VersionNumber.parseVersion("2.6.30"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Presto_browser_Opera_9() throws Exception {
		final String userAgent = "Opera/9.63 (X11; FreeBSD 7.1-RELEASE i386; U; en) Presto/2.1.1";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.PRESTO, "", VersionNumber.parseVersion("2.1.1"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Gecko_browser_Firefox_7() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 5.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.GECKO, "", VersionNumber.parseVersion("20100101"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Gecko_browser_Firefox_50() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 6.0; rv:50.0) Gecko/20100101 Firefox/50.0";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.GECKO, "", VersionNumber.parseVersion("20100101"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Gecko_browser_Firefox_58() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.GECKO, "", VersionNumber.parseVersion("20100101"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_Edge_browser_Edge_38() throws Exception {
		final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.EDGE, "", VersionNumber.parseVersion("14.14393"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

	@Test
	public void parse_engine_NetFront() throws Exception {
		final String userAgent = "ZTE-Z222/1.0.6 NetFront/3.5 QTV5.1 Profile/MIDP-2.1 Configuration/CLDC-1.1";
		final UserAgent agent = PARSER.parse(userAgent);
		assertThat(agent).isNotNull();

		// check browser engine informations
		final BrowserEngine actualBrowserEngine = agent.getBrowserEngine();
		final BrowserEngine expectedBrowserEngine = new BrowserEngine(BrowserEngineFamily.NETFRONT, "", VersionNumber.parseVersion("3.5"));
		assertThat(actualBrowserEngine.getFamily()).isEqualTo(expectedBrowserEngine.getFamily());
		assertThat(actualBrowserEngine.getVersionNumber()).isEqualTo(expectedBrowserEngine.getVersionNumber());
	}

}
