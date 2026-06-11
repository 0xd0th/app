(ns app.http.user 
    (:require [clj-http.client :as client] [cheshire.core :as json])
)

(def HOST "192.168.0.29")
(def PORT 3000 )
(def URI (format "http://%s:%d" HOST PORT))

(def OK 200)

(defn get_usuario []
  (let [response (client/get (str URI "/usuario"))]
    (if (= (:status response) OK)
        (json/parse-string (:body response) true)
        nil
    )
  )
)

(defn cadastrar_usuario [usuario]
    (client/post 
        (str URI "/usuario")
        {:headers {"Content-Type" "application/json"}
          :body (json/generate-string usuario)
        }
    )
)
