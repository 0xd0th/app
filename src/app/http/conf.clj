(ns app.http.conf)

(def HOST "192.168.0.29")
(def PORT 3000)

(def URI (format "http://%s:%d" HOST PORT))

(defn OK? [status] (= status 200))