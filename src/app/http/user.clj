(ns app.http.user 
    (:require [app.http.conf :refer :all] [clj-http.client :as client] [cheshire.core :as json])
)

(defn obter_usuario []
  (let [response (client/get (str URI "/usuario"))]
    (if (OK? (:status response) )
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
