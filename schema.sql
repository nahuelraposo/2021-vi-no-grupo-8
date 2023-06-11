
    create table Asociaciones (
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table Asociaciones_Preguntas (
        Asociaciones_id bigint not null,
        preguntasConRespuestasPosibles_id bigint not null
    )

    create table CaracteristicaIdeal (
        tipo varchar(31) not null,
        id bigint generated by default as identity (start with 1),
        descripcion varchar(255),
        primary key (id)
    )

    create table CaracteristicaIdealBooleana_valoresPosibles (
        CaracteristicaIdealBooleana_id bigint not null,
        valoresPosiblesBooleanos boolean
    )

    create table CaracteristicaIdealNumerica_valoresPosibles (
        CaracteristicaIdealNumerica_id bigint not null,
        valoresPosiblesNumericos integer
    )

    create table CaracteristicaIdealStrings_valoresPosibles (
        CaracteristicaIdealStrings_id bigint not null,
        valoresPosiblesString varchar(255)
    )

    create table CaracteristicasSensibles (
        tipo varchar(31) not null,
        id bigint generated by default as identity (start with 1),
        valorNumerico integer,
        valorBooleano boolean,
        valorString varchar(255),
        tipoCaracteristica_id bigint,
        primary key (id)
    )

    create table Chapitas (
        id integer not null,
        duenio_id bigint,
        primary key (id)
    )

    create table Duenios (
        id bigint generated by default as identity (start with 1),
        datosDeLogin_id bigint,
        persona_id bigint,
        primary key (id)
    )

    create table Duenios_Notificador (
        Duenios_id bigint not null,
        notificadores_id bigint not null
    )

    create table MascotaPerdida_caracteristicas (
        MascotaPerdida_id bigint not null,
        caracteristicas varchar(255)
    )

    create table MascotaPerdida_fotos (
        MascotaPerdida_id bigint not null,
        fotos varchar(255)
    )

    create table Mascota_fotos (
        Mascota_id bigint not null,
        fotos varchar(255)
    )

    create table Mascotas (
        id bigint generated by default as identity (start with 1),
        apodo varchar(255),
        descripcionFisica varchar(255),
        edad integer not null,
        nombre varchar(255),
        sexo char(255) not null,
        tamanioMascota integer,
        tipo integer,
        chapita_id integer,
        duenio_id bigint,
        primary key (id)
    )

    create table MascotasPerdidas (
        id bigint generated by default as identity (start with 1),
        descripcionEstado varchar(255),
        direccion varchar(255),
        latitud double,
        longitud double,
        tamanio integer,
        tipo integer,
        primary key (id)
    )

    create table Mascotas_CaracteristicasSensibles (
        Mascotas_id bigint not null,
        caracteristicas_id bigint not null
    )

    create table Notificador (
        tipo varchar(2) not null,
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table Persona_contactos (
        Persona_id bigint not null,
        apellidoContacto varchar(255),
        emailContacto varchar(255),
        nombreContacto varchar(255),
        telefonoContacto integer
    )

    create table Personas (
        id bigint generated by default as identity (start with 1),
        apellido varchar(255),
        numero integer not null,
        tipo integer,
        nacimiento varbinary(255),
        nombre varchar(255),
        primary key (id)
    )

    create table Preferencias (
        id bigint generated by default as identity (start with 1),
        edad integer not null,
        sexo char(255) not null,
        tamanio integer,
        tipoMascota integer,
        primary key (id)
    )

    create table Preferencias_CaracteristicasSensibles (
        Preferencias_id bigint not null,
        caracteristicas_id bigint not null
    )

    create table Pregunta_respuestasPosibles (
        Pregunta_id bigint not null,
        respuestasPosibles varchar(255)
    )

    create table Preguntas (
        id bigint generated by default as identity (start with 1),
        preguntaAdoptante varchar(255),
        preguntaDador varchar(255),
        primary key (id)
    )

    create table PreguntasRespondidas (
        id bigint generated by default as identity (start with 1),
        preguntaAdoptante varchar(255),
        preguntaDador varchar(255),
        respuesta varchar(255),
        publicaciones_mascotas_en_adopcion_id bigint,
        publicaciones_interesado_id bigint,
        primary key (id)
    )

    create table PublicacionesConChapita (
        id bigint generated by default as identity (start with 1),
        direccion varchar(255),
        fechaEncuentro varbinary(255),
        chapitaMascotaPerdida_id integer,
        mascotaPerdida_id bigint,
        rescatista_id bigint,
        primary key (id)
    )

    create table PublicacionesInteresados (
        id bigint generated by default as identity (start with 1),
        apellidoContacto varchar(255),
        emailContacto varchar(255),
        nombreContacto varchar(255),
        telefonoContacto integer,
        email varchar(255),
        preferencias_id bigint,
        asociacion_id bigint,
        primary key (id)
    )

    create table PublicacionesMascotasEnAdopcion (
        id bigint generated by default as identity (start with 1),
        duenio_id bigint,
        mascota_id bigint,
        asociacion_id bigint,
        primary key (id)
    )

    create table PublicacionesSinChapita (
        id bigint generated by default as identity (start with 1),
        aprobada boolean,
        direccion varchar(255),
        fechaEncuentro varbinary(255),
        mascotaPerdida_id bigint,
        rescatista_id bigint,
        asociacion_id bigint,
        primary key (id)
    )

    create table Usuarios (
        id bigint generated by default as identity (start with 1),
        admin boolean,
        password varchar(255),
        username varchar(255),
        primary key (id)
    )

    alter table Asociaciones_Preguntas 
        add constraint FK_o7wjo8hs2in6sr1onfp23u90p 
        foreign key (preguntasConRespuestasPosibles_id) 
        references Preguntas

    alter table Asociaciones_Preguntas 
        add constraint FK_l2ilru9fmq76skflalb578r53 
        foreign key (Asociaciones_id) 
        references Asociaciones

    alter table CaracteristicaIdealBooleana_valoresPosibles 
        add constraint FK_tmpas7jmhkfh3ef1f3veu7gnx 
        foreign key (CaracteristicaIdealBooleana_id) 
        references CaracteristicaIdeal

    alter table CaracteristicaIdealNumerica_valoresPosibles 
        add constraint FK_egkpfkwgf4b8qhy5e9de149we 
        foreign key (CaracteristicaIdealNumerica_id) 
        references CaracteristicaIdeal

    alter table CaracteristicaIdealStrings_valoresPosibles 
        add constraint FK_4xty27pqb8npfkd31w0mct9lb 
        foreign key (CaracteristicaIdealStrings_id) 
        references CaracteristicaIdeal

    alter table CaracteristicasSensibles 
        add constraint FK_p0pqs2yylxq4g6aukbdkk4vxx 
        foreign key (tipoCaracteristica_id) 
        references CaracteristicaIdeal

    alter table Chapitas 
        add constraint FK_f4fkjw7dijll5oyr7jjs1r6xb 
        foreign key (duenio_id) 
        references Duenios

    alter table Duenios 
        add constraint FK_1ydhorny5te4v6lteq7q1pu8x 
        foreign key (datosDeLogin_id) 
        references Usuarios

    alter table Duenios 
        add constraint FK_ill2sm0435m4xkkhlivk12u7v 
        foreign key (persona_id) 
        references Personas

    alter table Duenios_Notificador 
        add constraint FK_60n5mw119pmfb4dcbpho38709 
        foreign key (notificadores_id) 
        references Notificador

    alter table Duenios_Notificador 
        add constraint FK_8r4yjmublrcbkwnbb3n4rpbob 
        foreign key (Duenios_id) 
        references Duenios

    alter table MascotaPerdida_caracteristicas 
        add constraint FK_tjqe0wf31fotsqinebqnwvub6 
        foreign key (MascotaPerdida_id) 
        references MascotasPerdidas

    alter table MascotaPerdida_fotos 
        add constraint FK_aj4yt5pttjnxqgbcwe3u73e6i 
        foreign key (MascotaPerdida_id) 
        references MascotasPerdidas

    alter table Mascota_fotos 
        add constraint FK_dclevnore3slbildn3hia9nlo 
        foreign key (Mascota_id) 
        references Mascotas

    alter table Mascotas 
        add constraint FK_d1ew6xg7ga2cp60kot9ue1n97 
        foreign key (chapita_id) 
        references Chapitas

    alter table Mascotas 
        add constraint FK_nvg31pbtdkdobq5ip1iy04tu9 
        foreign key (duenio_id) 
        references Duenios

    alter table Mascotas_CaracteristicasSensibles 
        add constraint FK_8x99rkktf7s8c4m7s46httr3a 
        foreign key (caracteristicas_id) 
        references CaracteristicasSensibles

    alter table Mascotas_CaracteristicasSensibles 
        add constraint FK_68vdbv0ggek3ps41bh10627vk 
        foreign key (Mascotas_id) 
        references Mascotas

    alter table Persona_contactos 
        add constraint FK_1aga9q3lt5vfk6qob73tbhhov 
        foreign key (Persona_id) 
        references Personas

    alter table Preferencias_CaracteristicasSensibles 
        add constraint FK_e859mxt47yqu8ocss3y83t8br 
        foreign key (caracteristicas_id) 
        references CaracteristicasSensibles

    alter table Preferencias_CaracteristicasSensibles 
        add constraint FK_ddhulh3ic81aj19ip0ut1ases 
        foreign key (Preferencias_id) 
        references Preferencias

    alter table Pregunta_respuestasPosibles 
        add constraint FK_tljf67s8xhmcw6lpfv8cmbyxf 
        foreign key (Pregunta_id) 
        references Preguntas

    alter table PreguntasRespondidas 
        add constraint FK_6ulao3rskc2k0vujtrbpnm654 
        foreign key (publicaciones_mascotas_en_adopcion_id) 
        references PublicacionesMascotasEnAdopcion

    alter table PreguntasRespondidas 
        add constraint FK_fxi57cgfqhr1o248hdaq8gabx 
        foreign key (publicaciones_interesado_id) 
        references PublicacionesInteresados

    alter table PublicacionesConChapita 
        add constraint FK_kjk10riaot7t2phhruwmoqtpm 
        foreign key (chapitaMascotaPerdida_id) 
        references Chapitas

    alter table PublicacionesConChapita 
        add constraint FK_j4x3hgu4tueeasaateyc2jj3m 
        foreign key (mascotaPerdida_id) 
        references MascotasPerdidas

    alter table PublicacionesConChapita 
        add constraint FK_rdrubstsrx99t9kbxcerey71w 
        foreign key (rescatista_id) 
        references Personas

    alter table PublicacionesInteresados 
        add constraint FK_huo451r9srwnj8uhwi7jemw7o 
        foreign key (preferencias_id) 
        references Preferencias

    alter table PublicacionesInteresados 
        add constraint FK_kaf39v85tcl953o0bcaw52v3s 
        foreign key (asociacion_id) 
        references Asociaciones

    alter table PublicacionesMascotasEnAdopcion 
        add constraint FK_34luppfdoy96ud98ejqma585v 
        foreign key (duenio_id) 
        references Duenios

    alter table PublicacionesMascotasEnAdopcion 
        add constraint FK_6l2dp12ajwt23quuu89nal20y 
        foreign key (mascota_id) 
        references Mascotas

    alter table PublicacionesMascotasEnAdopcion 
        add constraint FK_689rm3jg7g3iddvp4sg6abkc6 
        foreign key (asociacion_id) 
        references Asociaciones

    alter table PublicacionesSinChapita 
        add constraint FK_ivg43l853c2r8cblqr9mf1cvm 
        foreign key (mascotaPerdida_id) 
        references MascotasPerdidas

    alter table PublicacionesSinChapita 
        add constraint FK_iwcmo5ck35tk4phf6r9o4n76w 
        foreign key (rescatista_id) 
        references Personas

    alter table PublicacionesSinChapita 
        add constraint FK_g0xj89pqq2tb064v8eq1mrvy5 
        foreign key (asociacion_id) 
        references Asociaciones