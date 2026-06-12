(ns app.http.transaction
    (:require [app.http.conf :refer :all] [clj-http.client :as client] [cheshire.core :as json])
)

(defn obter_saldo_calorias [data]
    (client/get
        (str URI "/saldo")
        {:query-params {:data data}}
    )
)

(defn obter_registro_por_data [data]
    (client/get 
        (str URI "/registros")
        {:query-params {:data data}}
    )
)

(defn registrar_consumo [consumo]
    (client/post 
        (str URI "/consumo/registrar")
        {:headers {"Content-Type" "application/json"}
          :body (json/generate-string consumo)
        }
    ) 
)

(defn registrar_atividade [atividade] 
    (client/post 
        (str URI "/atividade/registrar")
        {
          :headers {"Content-Type" "application/json"}
          :body (json/generate-string atividade)
        }
    ) 
)
