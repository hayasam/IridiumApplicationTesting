package au.com.agic.apptesting.utils.impl;

import au.com.agic.apptesting.profiles.configuration.UrlMapping;
import au.com.agic.apptesting.utils.FeatureState;
import au.com.agic.apptesting.utils.ProxyDetails;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents the details required by a feature to run
 */
public class FeatureStateImpl implements FeatureState {

	/**
	 * Wait a second between steps by default
     */
	private static final int DEFAULT_WAIT_TIME = 1000;

	private final UrlMapping url;
	private final Map<String, String> dataset = new HashMap<>();
	private final String reportDirectory;
	private boolean failed;
	private List<ProxyDetails<?>> proxies;
	private long sleep = DEFAULT_WAIT_TIME;

	public FeatureStateImpl(
		@NotNull final UrlMapping url,
		@NotNull final Map<String, String> dataset,
		@NotNull final String reportDirectory,
		@NotNull final List<ProxyDetails<?>> proxies) {
		checkNotNull(url);

		this.url = url;
		this.dataset.clear();
		this.dataset.putAll(dataset);
		this.reportDirectory = reportDirectory;
		this.proxies = new ArrayList<>(proxies);
	}

	@Override
	public void setDefaultSleep(final long defaultSleep) {
		this.sleep = defaultSleep;
	}

	@Override
	public long getDefaultSleep() {
		return sleep;
	}

	@Override
	public UrlMapping getUrlDetails() {
		return url;
	}

	@Override
	public Map<String, String> getDataSet() {
		return new HashMap<>(dataset);
	}

	@Override
	public void setDataSet(final Map<String, String> dataSet) {
		dataset.clear();
		if (dataSet != null) {
			dataset.putAll(dataSet);
		}
	}

	@Override
	public boolean getFailed() {
		return failed;
	}

	@Override
	public void setFailed(final boolean failed) {
		this.failed = failed;
	}

	@Override
	public String getReportDirectory() {
		return reportDirectory;
	}

	@Override
	public List<ProxyDetails<?>> getProxyInterface() {
		return proxies;
	}

	@Override
	public Optional<ProxyDetails<?>> getProxyInterface(@NotNull final String name) {
		checkArgument(StringUtils.isNotBlank(name));

		return proxies.stream()
			.filter(x -> name.equals(x.getProxyName()))
			.findFirst();
	}

	@Override
	public void setProxyInterface(final List<ProxyDetails<?>> myProxies) {
		this.proxies = new ArrayList<>(myProxies);
	}
}