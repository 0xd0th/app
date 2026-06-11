(ns app.core
  (:require [app.http.user :as user] )
  (:gen-class)
)

(defn usuario_mapper [altura peso idade sexo]
    {
        :altura altura
        :peso peso
        :idade idade
        :sexo sexo
    }
)

(defn pergunta [texto]
  (print texto)
  (flush)
  (read)
)

(defn menu-cadastro []
  ( if-let [ {:keys [altura peso idade sexo]} (user/get_usuario) ]
    ( printf "\nperfil do usuário:\naltura: %.2f\npeso: %.2f\nidade: %d\nsexo: %s\n\n"
      altura, peso, idade, sexo
    )
    (do
      (printf "cadastro de usuário:\n")
      (flush)
      (let [ usuario (usuario_mapper (pergunta "idade: ")
             (pergunta "altura: ")
             (pergunta "peso: ")
             (pergunta "sexo: ")) ]
      
        (user/cadastrar_usuario usuario)
      )
    )
    
  )

)

(defn menu []
  (printf "------------------ menu ------------------
1) cadastrar/consultar dados pessoais
2) registrar consumo de alimento
3) registrar realização de atividade física
4) consultar extrato de transações
5) consultar saldo de calorias
0) sair
--> "
  )
  
  (flush)

  (let [opt (read)]
    (cond
      (= opt 0 ) (System/exit 0)
      (= opt 1 ) (menu-cadastro)
      ;(= opt 2 ) (registrar_consumo )
      ;(= opt 3 ) (registrar_atividade )
      ;(= opt 4 ) (consultar_cadastro_trasacoes )
      ;(= opt 5 ) (consultar_saldo_calorias )
    )
  )

  (recur)
)


(defn -main
  [& args]

  (menu)
  
)



