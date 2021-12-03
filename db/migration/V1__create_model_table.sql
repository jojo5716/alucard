CREATE TABLE public.models (
    id uuid NOT NULL,
    name varchar(120),
    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW(),
    CONSTRAINT models_pkey PRIMARY KEY (id)
);