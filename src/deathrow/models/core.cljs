(ns deathrow.models.core
	(:require [jayq.core :as jayq :refer [$ ajax]]
		[deathrow.history :as h :refer [dispatch!]]
		[deathrow.constants :as C]))

(def $nav ($ :.nav))

(defn render
	[$elem view]
	(-> $elem
		.empty
		(.append view)))

(defn block-internal-urls
	[]
	(.delegate ($ js/document) :a "click"
		(fn [e]
			(when (= (.-hostname (.-target e)) (-> js/window .-location .-hostname))
				(do (.preventDefault e)
					(h/dispatch! (.-pathname (.-target e)))
					(let [active (.find $nav ".active")
						clicked (.find $nav (str "[href=\"" (.-pathname (.-target e)) "\"]"))
						in-nav? (> (.-length clicked) 0)]
							(when in-nav?
								(do (.removeClass active "active")
									(.addClass (.parent clicked) "active")))))))))

(defn handle-active-states
	[]
	)

(defn get-ajax
    [path settings]
    (ajax (str C/AJAX-ENDPOINT path)
        (merge
        	{:dataType "json"
        	:timeout 15000} settings)))
