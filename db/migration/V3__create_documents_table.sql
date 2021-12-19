CREATE TABLE public.documents (
    id uuid NOT NULL,
    models_id uuid constraint fields_model_fk references models,

    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW(),

    CONSTRAINT documents_pkey PRIMARY KEY (id)
);