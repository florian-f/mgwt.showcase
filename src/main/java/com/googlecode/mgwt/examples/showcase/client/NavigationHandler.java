package com.googlecode.mgwt.examples.showcase.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.activities.AboutPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.UIEntrySelectedEvent;
import com.googlecode.mgwt.examples.showcase.client.activities.UIEntrySelectedEvent.UIEntry;
import com.googlecode.mgwt.examples.showcase.client.activities.UIPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.Animation;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.Animation.AnimationNames;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationSelectedEvent;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDissolvePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFadePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFlipPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationPopPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlidePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlideUpPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSwapPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.button.ButtonPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.buttonbar.ButtonBarPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.elements.ElementsPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.popup.PopupPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.progressbar.ProgressBarPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.progressindicator.ProgressIndicatorPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh.PullToRefreshPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.scrollwidget.ScrollWidgetPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.searchbox.SearchBoxPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.slider.SliderPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.tabbar.TabBarPlace;
import com.googlecode.mgwt.examples.showcase.client.event.ActionEvent;
import com.googlecode.mgwt.examples.showcase.client.event.ActionNames;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.history.Html5Historian;
import com.googlecode.mgwt.mvp.client.history.PopStateEvent;
import com.googlecode.mgwt.mvp.client.history.PopStateHandler;
import com.googlecode.mgwt.ui.client.MGWT;

public class NavigationHandler {

	protected static Logger log = Logger.getLogger(NavigationHandler.class.getName());

	private EventBus eventBus;
	private ClientFactory clientFactory;
	private final PlaceHistoryMapper placeHistoryMapper;

	private static final Historian GWT_historian = (Historian) GWT.create(DefaultHistorian.class);

	private static final Html5Historian historian = (Html5Historian) GWT.create(Html5Historian.class);

	private PlaceController placeController;

	private boolean ignore;

	public NavigationHandler(PlaceHistoryMapper placeHistoryMapper) {

		this.placeHistoryMapper = placeHistoryMapper;

	}

	public void register(PlaceController placeController, EventBus eventBus, Place defaultPlace) {
		this.placeController = placeController;
		this.eventBus = eventBus;
		this.defaultPlace = defaultPlace;
		// TODO deregister
		bind();
	}

	private void bind() {
		eventBus.addHandler(AnimationSelectedEvent.getType(), new AnimationSelectedEvent.Handler() {

			@Override
			public void onAnimationSelected(AnimationSelectedEvent event) {

				Animation animation = event.getAnimation();

				AnimationNames animationName = animation.getAnimationName();

				Place place = null;

				switch (animationName) {
				case SLIDE:
					place = new AnimationSlidePlace();

					break;
				case SLIDE_UP:
					place = new AnimationSlideUpPlace();

					break;
				case DISSOLVE:
					place = new AnimationDissolvePlace();

					break;
				case FADE:
					place = new AnimationFadePlace();

					break;
				case FLIP:
					place = new AnimationFlipPlace();

					break;
				case POP:
					place = new AnimationPopPlace();

					break;
				case SWAP:
					place = new AnimationSwapPlace();

					break;

				default:
					// TODO log
					place = new AnimationSlidePlace();
					break;
				}

				if (MGWT.getOsDetection().isTablet()) {
					ignore = true;
					replaceToken(tokenForPlace(place));
				}

				placeController.goTo(place);

			}
		});
		UIEntrySelectedEvent.register(eventBus, new UIEntrySelectedEvent.Handler() {

			@Override
			public void onAnimationSelected(UIEntrySelectedEvent event) {

				UIEntry entry = event.getEntry();

				Place place = null;

				switch (entry) {
				case BUTTON_BAR:
					place = new ButtonBarPlace();
					break;
				case BUTTONS:
					place = new ButtonPlace();
					break;
				case ELEMENTS:
					place = new ElementsPlace();
					break;
				case POPUPS:
					place = new PopupPlace();
					break;
				case PROGRESS_BAR:
					place = new ProgressBarPlace();
					break;
				case PROGRESS_INDICATOR:
					place = new ProgressIndicatorPlace();
					break;
				case PULL_TO_REFRESH:
					place = new PullToRefreshPlace();
					break;
				case SCROLL_WIDGET:
					place = new ScrollWidgetPlace();
					break;
				case SEARCH_BOX:
					place = new SearchBoxPlace();
					break;
				case SLIDER:
					place = new SliderPlace();
					break;
				case TABBAR:
					place = new TabBarPlace();
					break;

				default:
					break;
				}

				if (MGWT.getOsDetection().isTablet()) {
					ignore = true;
					replaceToken(tokenForPlace(place));
				}

				placeController.goTo(place);

			}
		});

		ActionEvent.register(eventBus, ActionNames.BACK, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {

				History.back();

			}
		});

		ActionEvent.register(eventBus, ActionNames.ANIMATION_END, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {
				if (MGWT.getOsDetection().isPhone()) {
					History.back();
				} else {
					ignore = true;
					placeController.goTo(new AnimationPlace());
				}

			}
		});

		historian.addPopStateHandler(new PopStateHandler() {

			@Override
			public void onPopStateEvent(PopStateEvent event) {

				onPopStateEventOccured(event.getData());

			}
		});

		eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {

			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				if (ignore) {
					ignore = false;
					return;
				}

				Place newPlace = event.getNewPlace();
				pushToken(tokenForPlace(newPlace));

			}
		});

	}

	protected void onPopStateEventOccured(String token) {
		Place place = getPlaceForToken(token);
		ignore = true;
		placeController.goTo(place);
	}

	private void onPhoneNav(Place place) {
		if (place instanceof AnimationDissolvePlace || place instanceof AnimationFadePlace || place instanceof AnimationFlipPlace || place instanceof AnimationPopPlace
				|| place instanceof AnimationSlidePlace || place instanceof AnimationSlideUpPlace || place instanceof AnimationSwapPlace) {

			String token = placeHistoryMapper.getToken(new HomePlace());
			replaceToken(token);

			token = placeHistoryMapper.getToken(new AnimationPlace());
			pushToken(token);

		} else {
			if (place instanceof AboutPlace) {
				String token = placeHistoryMapper.getToken(new HomePlace());
				replaceToken(token);
			} else {
				if (place instanceof AnimationPlace) {
					String token = placeHistoryMapper.getToken(new HomePlace());
					replaceToken(token);
				} else {
					if (place instanceof UIPlace) {
						String token = placeHistoryMapper.getToken(new HomePlace());
						replaceToken(token);
					} else {
						if (place instanceof UIPlace) {
							String token = placeHistoryMapper.getToken(new HomePlace());
							replaceToken(token);
						} else {

							if (place instanceof ButtonBarPlace || place instanceof ButtonPlace || place instanceof ElementsPlace || place instanceof PopupPlace || place instanceof ProgressBarPlace
									|| place instanceof ProgressIndicatorPlace || place instanceof PullToRefreshPlace || place instanceof ScrollWidgetPlace || place instanceof SearchBoxPlace
									|| place instanceof SliderPlace || place instanceof TabBarPlace) {
								String token = placeHistoryMapper.getToken(new HomePlace());
								replaceToken(token);

								token = placeHistoryMapper.getToken(new UIPlace());
								pushToken(token);
							}

						}
					}
				}
			}
		}
	}

	private void onTabletNav(Place place) {
		if (place instanceof AnimationDissolvePlace || place instanceof AnimationFadePlace || place instanceof AnimationFlipPlace || place instanceof AnimationPopPlace
				|| place instanceof AnimationSlidePlace || place instanceof AnimationSlideUpPlace || place instanceof AnimationSwapPlace) {

			String token = placeHistoryMapper.getToken(new HomePlace());
			replaceToken(token);

		} else {
			if (place instanceof AboutPlace) {
				String token = placeHistoryMapper.getToken(new HomePlace());
				replaceToken(token);
			} else {
				if (place instanceof AnimationPlace) {
					String token = placeHistoryMapper.getToken(new HomePlace());
					replaceToken(token);
				} else {
					if (place instanceof UIPlace) {
						String token = placeHistoryMapper.getToken(new HomePlace());
						replaceToken(token);
					} else {
						if (place instanceof UIPlace) {
							String token = placeHistoryMapper.getToken(new HomePlace());
							replaceToken(token);
						} else {

							if (place instanceof ButtonBarPlace || place instanceof ButtonPlace || place instanceof ElementsPlace || place instanceof PopupPlace || place instanceof ProgressBarPlace
									|| place instanceof ProgressIndicatorPlace || place instanceof PullToRefreshPlace || place instanceof ScrollWidgetPlace || place instanceof SearchBoxPlace
									|| place instanceof SliderPlace || place instanceof TabBarPlace) {
								String token = placeHistoryMapper.getToken(new HomePlace());
								replaceToken(token);

							}

						}
					}
				}
			}
		}
	}

	protected void replaceToken(String token) {
		historian.replaceState(token, Window.getTitle(), "#" + token);
	}

	protected void pushToken(String token) {
		historian.pushState(token, Window.getTitle(), "#" + token);
	}

	public void handleCurrentHistory() {
		Place place = getPlaceForToken(GWT_historian.getToken());

		// TODO in extra interface!
		if (MGWT.getOsDetection().isPhone()) {
			onPhoneNav(place);
		} else {
			// tablet
			onTabletNav(place);

		}

		if (defaultPlace.equals(place)) {
			ignore = true;
		}

		placeController.goTo(place);
	}

	private Place defaultPlace = Place.NOWHERE;

	protected Place getPlaceForToken(String token) {

		Place newPlace = null;

		if ("".equals(token)) {
			newPlace = defaultPlace;
		}

		if (newPlace == null) {
			newPlace = placeHistoryMapper.getPlace(token);
		}

		if (newPlace == null) {
			log.warning("Unrecognized history token: " + token);
			newPlace = defaultPlace;
		}
		return newPlace;

	}

	private String tokenForPlace(Place newPlace) {

		if (defaultPlace.equals(newPlace)) {
			return "";
		}

		String token = placeHistoryMapper.getToken(newPlace);
		if (token != null) {
			return token;
		}

		log.warning("Place not mapped to a token: " + newPlace);
		return "";
	}
}
