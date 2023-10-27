package br.com.b3social.acaosocialservice.domain.models.enums;

public enum Status {
        ABERTA("ABERTA"),
        FECHADA("FECHADA"),
        FINALIZADA("FINALIZADA");

        public final String label;

        private Status(String label) {
            this.label = label;
        }
    }