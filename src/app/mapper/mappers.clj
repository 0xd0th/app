(ns app.mapper.mappers)
(import '[java.time LocalDate])
(import '[java.time.format DateTimeFormatter])

(defn usuario_mapper [altura peso idade sexo]
    {
        :altura altura
        :peso peso
        :idade idade
        :sexo sexo
    }
)

(def fmt
  (DateTimeFormatter/ofPattern "dd-MM-yyyy"))

(defn parse-data [s]
  (str (LocalDate/parse s fmt)))

(defn consumo_mapper [data tipo_alimento quantidade ]
    {
        :data (parse-data data)
        :tipo_alimento tipo_alimento
        :quantidade (Integer/parseInt quantidade)
    }
)

(defn atividade_mapper [data tipo duracao]
    {
        :data (parse-data data)
        :tipo tipo
        :duracao (Integer/parseInt duracao)
    }
)