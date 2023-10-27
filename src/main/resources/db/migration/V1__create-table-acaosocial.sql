create table acaosocial (
    id TEXT UNIQUE NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    resumo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    nivel INTEGER NOT NULL,
    data_inicio DATE,
    data_termino DATE,
    banner TEXT,
    coordenador_id TEXT NOT NULL,
    coordenador_nome TEXT NOT NULL,
    coordenador_email VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    created_by TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    deleted_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ, 
    PRIMARY KEY (id)
);