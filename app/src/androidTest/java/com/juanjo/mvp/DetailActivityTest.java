package com.juanjo.mvp;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.parceler.Parcels;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import roboguice.RoboGuice;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.AbstractModule;
import com.juanjo.mvp.interfaces.IDetailActivityPresenter;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.presenters.DetailViewPresenter;
import com.juanjo.mvp.views.activities.DetailActivity;

/**
 * Created by juanjo on 20/03/15.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class DetailActivityTest {

	@Mock
	DetailViewPresenter presenterMock;

	DetailActivity activity;

	private static ImageDto IMAGE = new ImageDto();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		RoboGuice.overrideApplicationInjector(Robolectric.application,
				new MyTestModule());
		RoboGuice.getInjector(Robolectric.application).injectMembers(this);

		Intent intent = new Intent(Robolectric.getShadowApplication()
				.getApplicationContext(), DetailActivity.class);
		ImageDto imageDto = new ImageDto();
		intent.putExtra("IMAGE", Parcels.wrap(imageDto));

		activity = Robolectric.buildActivity(DetailActivity.class)
				.withIntent(intent).create().get();
	}

	@Test
	public void onCreateActivityTest() {

		ImageView image = (ImageView) activity.findViewById(R.id.image);
		TextView title = (TextView) activity.findViewById(R.id.title);

		Assert.assertNotNull(activity);
		Assert.assertNotNull(image);
		Assert.assertNotNull(title);
	}

	@Test
	public void onCreateActivityShouldCallToPresenterTest() {

		verify(presenterMock, times(1)).onCreate(eq(activity),
				any(ImageDto.class));
	}

	@Test
	public void testSetTitle() {
		TextView title = (TextView) activity.findViewById(R.id.title);
		title.setText("test");

		Assert.assertEquals("test", title.getText());
	}

	public class MyTestModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(IDetailActivityPresenter.class).toInstance(presenterMock);
		}
	}
}
