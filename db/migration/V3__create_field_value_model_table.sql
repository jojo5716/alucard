CREATE TABLE public.field_values (
    id uuid NOT NULL,
    value TEXT,
    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW(),

    field_id uuid constraint fields_fk references fields,

    CONSTRAINT field_values_pkey PRIMARY KEY (id)
);