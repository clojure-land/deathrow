(ns deathrow.core
	(:require
		[deathrow.util :refer [log]]
		[deathrow.routes :as routes]
		[deathrow.views.core :as view]))


(defn init
	[]
	(view/block-internal-urls))


(init)
