(ns deathrow.core
	(:require
		[deathrow.util :refer [log]]
		[deathrow.routes :as routes]))


(defn init
	[]
	(routes/block-internal-urls))


(init)
