CREATE TABLE public.model_fields (
    id uuid NOT NULL,
    name varchar(120),
    element varchar(20),
    type varchar(20),
    required BOOLEAN DEFAULT true,
    models_id uuid constraint model_fields_model_fk references models,

    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW(),
    CONSTRAINT model_fields_pkey PRIMARY KEY (id)
);