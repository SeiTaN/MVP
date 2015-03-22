package com.juanjo.mvp.roboguice.modules;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.juanjo.mvp.interfaces.IDetailViewPresenter;
import com.juanjo.mvp.interfaces.IMainViewPresenter;
import com.juanjo.mvp.presenters.DetailViewPresenter;
import com.juanjo.mvp.presenters.MainViewPresenter;

/**
 * Created by juanjo on 05/02/15.
 */
// Indicate to injector what class have to bind to what interface
public class MVPModule extends AbstractModule {
	private Application application;

	public MVPModule() {
	}

	public MVPModule(Application application) {
		this.application = application;
	}

	@Override
	protected void configure() {
		bind(IMainViewPresenter.class).to(MainViewPresenter.class);
		bind(IDetailViewPresenter.class).to(DetailViewPresenter.class);
	}
}