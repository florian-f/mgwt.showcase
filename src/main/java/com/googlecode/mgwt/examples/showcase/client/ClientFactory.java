/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.activities.about.AboutView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ShowCaseListView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.UIView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.animation.AnimationView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.animation.animationdone.AnimationDoneView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.button.ButtonView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.buttonbar.ButtonBarView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.carousel.CarouselView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.elements.ElementsView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.forms.FormsView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.gcell.GroupedCellListView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.popup.PopupView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.progressbar.ProgressBarView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.progressindicator.ProgressIndicatorView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.pulltorefresh.PullToRefreshDisplay;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.scrollwidget.ScrollWidgetView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.searchbox.SearchBoxView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.slider.SliderView;
import com.googlecode.mgwt.examples.showcase.client.activities.showcaselist.ui.tabbar.TabBarView;

/**
 * @author Daniel Kurka
 * 
 */
public interface ClientFactory {
	public ShowCaseListView getHomeView();

	public EventBus getEventBus();

	public PlaceController getPlaceController();

	/**
	 * @return
	 */
	public UIView getUIView();

	public AboutView getAboutView();

	public AnimationView getAnimationView();

	public AnimationDoneView getAnimationDoneView();

	public ScrollWidgetView getScrollWidgetView();

	public ElementsView getElementsView();

	public ButtonBarView getButtonBarView();

	public SearchBoxView getSearchBoxView();

	public TabBarView getTabBarView();

	public ButtonView getButtonView();

	/**
	 * 
	 */
	public PopupView getPopupView();

	public ProgressBarView getProgressBarView();

	public SliderView getSliderView();

	public CarouselView getCarouselHorizontalView();

	public PullToRefreshDisplay getPullToRefreshDisplay();

	public ProgressIndicatorView getProgressIndicatorView();

	public FormsView getFormsView();

	public GroupedCellListView getGroupedCellListView();

}
