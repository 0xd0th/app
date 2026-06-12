(ns app.core
  (:require [app.http.user :as user] 
            [app.http.transaction :as transaction]
            [app.mapper.mappers :as mp]
  )
  (:gen-class)
)


(defn pergunta [texto]
  (print texto)
  (flush)
  (read-line)
)

(defn menu-cadastro []
  ( if-let [ {:keys [altura peso idade sexo]} (user/obter_usuario) ]
    ( printf "\nperfil do usuário:\naltura: %.2f\npeso: %.2f\nidade: %d\nsexo: %s\n\n"
      altura, peso, idade, sexo
    )
    (do
      (printf "\ncadastro de usuário:\n")
      (flush)
      (let [ usuario (mp/usuario_mapper (pergunta "idade: ")
             (pergunta "altura: ")
             (pergunta "peso: ")
             (pergunta "sexo: ")) ]
      
        (user/cadastrar_usuario usuario)
      )
    )
    
  )
)

(defn menu_consumo []
  (printf "\ncadastro de consumo:\n")
  (flush)
  (let [ consumo (mp/consumo_mapper (pergunta "data[dd-mm-yyyy]: ")
    (pergunta "alimento: ")
    (pergunta "quantidade[em g]: ")) ]

    (transaction/registrar_consumo consumo)
    (println "consumacao registrada com sucesso!!!\n")
  )
)

(defn menu_atividade []
  (printf "\ncadastro de atividade:\n")
  (flush)
  (let [ atividade (mp/atividade_mapper (pergunta "data[dd-mm-yyyy]: ")
    (pergunta "tipo: ")
    (pergunta "duracao[em s]: ")) ]

    (transaction/registrar_atividade atividade)
    (println "atividade registrada com sucesso!!!\n")
  )
)

(defn historico_trasacoes []
  (printf "\nhistorico de transacoes\n\n")
  (let [data (pergunta "data[dd-mm-yyyy]: ")
        transacoes (transacoes/obter_registro_por_data data)])
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

  (let [opt (Integer/parseInt (read-line))]
    (cond
      (= opt 0 ) (System/exit 0)
      (= opt 1 ) (menu-cadastro)
      (= opt 2 ) (menu_consumo)
      (= opt 3 ) (menu_atividade)
      (= opt 4 ) (historico_trasacoes)
      (= opt 5 ) (saldo_calorias)
      :else (System/exit 0)
    )
  )

  (recur)
)


(defn -main
  [& args]

  (menu)
  
)



