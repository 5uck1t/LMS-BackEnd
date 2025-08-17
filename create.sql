
    create table adresa (
        obrisano bit default false,
        id bigint not null auto_increment,
        mesto_id bigint,
        broj varchar(255),
        ulica varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table datum_predmeta (
        obrisano bit default false,
        datum datetime(6),
        id bigint not null auto_increment,
        realizacija_predmeta_id bigint,
        rok_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table dodeljeno_pravo_pristupa (
        obrisano bit default false,
        id bigint not null auto_increment,
        pravo_pristupa_id bigint,
        ulogovani_korisnik_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table drzava (
        obrisano bit,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table evaluacija_znanja (
        obrisano bit default false,
        id bigint not null,
        realizacija_predmeta_id bigint,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table fakultet (
        obrisano bit default false,
        dekan_id bigint,
        id bigint not null auto_increment,
        univerzitet_id bigint,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table forum (
        obrisano bit default false,
        forum_id bigint,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table forum_has_korisnik (
        obrisano bit default false,
        forum_id bigint,
        id bigint not null,
        ulogovani_korisnik_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table godina_studija (
        obrisano bit default false,
        id bigint not null auto_increment,
        studijski_program_id bigint,
        godina varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table katedra (
        obrisano bit default false,
        fakultet_id bigint,
        id bigint not null auto_increment,
        sef_katedre_id bigint,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table mesto (
        obrisano bit default false,
        drzava_id bigint,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table nastavnik (
        obrisano bit default false,
        id bigint not null auto_increment,
        osoba_id bigint,
        biografija varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table nastavnik_has_zvanje (
        obrisano bit default false,
        id bigint not null auto_increment,
        nastavnik_id bigint,
        zvanje_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table naucna_oblast (
        obrisano bit default false,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table obavestenje (
        obrisano bit default false,
        forum_id bigint,
        id bigint not null auto_increment,
        korisnik_id bigint,
        vreme_postavljanja datetime(6),
        naslov varchar(255),
        tekst_obavjestenja varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table odgovor (
        obrisano bit default false,
        id bigint not null auto_increment,
        zadatak_id bigint,
        odgovor varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table osoba (
        obrisano bit default false,
        adresa_id bigint,
        id bigint not null auto_increment,
        ime varchar(255),
        jmbg varchar(255),
        prezime varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table pohadjanje_predmeta (
        konacna_ocena integer not null,
        obrisano bit default false,
        id bigint not null auto_increment,
        realizacija_predmeta_id bigint,
        student_na_godini_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table polaganje (
        obrisano bit default false,
        datum datetime(6),
        evaluacija_znanja_id bigint,
        id bigint not null auto_increment,
        rok_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table pravo_pristupa (
        obrisano bit default false,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table predmet (
        broj_predavanja integer,
        broj_vezbi integer,
        espb integer,
        obavezan bit,
        obrisano bit default false,
        id bigint not null auto_increment,
        broj_semestara varchar(255),
        drugi_oblici_nastave varchar(255),
        istrazivacki_rad varchar(255),
        naziv varchar(255),
        ostali_casovi varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table prijava_polaganja (
        broj_bodova float(53),
        obrisano bit,
        datum datetime(6),
        id bigint not null auto_increment,
        pohadjanje_predmeta_id bigint,
        polaganje_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table realizacija_predmeta (
        obrisano bit default false,
        godina_studija_id bigint,
        id bigint not null auto_increment,
        nastavnik_id bigint,
        predmet_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table rok (
        obrisano bit default false,
        id bigint not null auto_increment,
        kraj datetime(6),
        pocetak datetime(6),
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table silabus (
        obrisano bit default false,
        id bigint not null auto_increment,
        predmet_id bigint,
        opis varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table student (
        obrisano bit default false,
        id bigint not null auto_increment,
        osoba_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table student_na_godini (
        obrisano bit default false,
        broj_indeksa bigint,
        datum_upisa datetime(6),
        godina_studija_id bigint,
        id bigint not null auto_increment,
        student_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table studijski_program (
        obrisano bit default false,
        id bigint not null auto_increment,
        katedra_id bigint,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table termin (
        datum date,
        obrisano bit,
        vreme_kraja time(6),
        vreme_pocetka time(6),
        id bigint not null auto_increment,
        realizacija_predmeta_id bigint,
        ishod varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tip_zvanja (
        obrisano bit default false,
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table ulogovani_korisnik (
        obrisano bit default false,
        id bigint not null,
        osoba_id bigint,
        email varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table univerzitet (
        obrisano bit default false,
        adresa_id bigint,
        datum_osnivanja datetime(6),
        id bigint not null auto_increment,
        naziv varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table zadatak (
        obrisano bit default false,
        evaluacija_znanja_id bigint,
        id bigint not null auto_increment,
        pitanje varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table zvanje (
        obrisano bit default false,
        datum_izbora datetime(6),
        datum_prestanka datetime(6),
        id bigint not null auto_increment,
        naucna_oblast_id bigint,
        tip_zvanja_id bigint,
        primary key (id)
    ) engine=InnoDB;

    alter table fakultet 
       add constraint UKtbfrr0newauh6c2ko3jfiebnr unique (dekan_id);

    alter table nastavnik 
       add constraint UK5atsvc6cuyk8whlib3rjbb688 unique (osoba_id);

    alter table student 
       add constraint UKpc4jt1n1u4jyhucoxjq4sf2vc unique (osoba_id);

    alter table ulogovani_korisnik 
       add constraint UK2lgkrfpihrohl4kc24t9sgv7b unique (osoba_id);

    alter table adresa 
       add constraint FKs3cl12kvk7ugqu3wi2u9y7vd0 
       foreign key (mesto_id) 
       references mesto (id);

    alter table datum_predmeta 
       add constraint FK4atk3jk85vlm8l9w0nojfs5q9 
       foreign key (realizacija_predmeta_id) 
       references realizacija_predmeta (id);

    alter table datum_predmeta 
       add constraint FKtmn63tqlix9cn5bbnuqqxubn4 
       foreign key (rok_id) 
       references rok (id);

    alter table dodeljeno_pravo_pristupa 
       add constraint FKgn6u5s1rthyc1jns895g44ym6 
       foreign key (pravo_pristupa_id) 
       references pravo_pristupa (id);

    alter table dodeljeno_pravo_pristupa 
       add constraint FKjkkpq4brjhm79n5jacwe5dwkc 
       foreign key (ulogovani_korisnik_id) 
       references ulogovani_korisnik (id);

    alter table evaluacija_znanja 
       add constraint FKmicppx3e9v4gl9wn4i6l46v7p 
       foreign key (realizacija_predmeta_id) 
       references realizacija_predmeta (id);

    alter table fakultet 
       add constraint FKop18q47667vp5icvvh0qr8tjo 
       foreign key (dekan_id) 
       references nastavnik (id);

    alter table fakultet 
       add constraint FKc9lc5sekwpb19qpobc7eegjpn 
       foreign key (univerzitet_id) 
       references univerzitet (id);

    alter table forum 
       add constraint FKb7dsponnmyqts6wxbgmnyyfq4 
       foreign key (forum_id) 
       references forum (id);

    alter table forum_has_korisnik 
       add constraint FKghegxrmlnna8wbtwjcky30yg1 
       foreign key (forum_id) 
       references forum (id);

    alter table forum_has_korisnik 
       add constraint FK1p8xtqqafgye1etsbjgpvx2yw 
       foreign key (ulogovani_korisnik_id) 
       references ulogovani_korisnik (id);

    alter table godina_studija 
       add constraint FK365x07l7paf40u1xlbabq84gq 
       foreign key (studijski_program_id) 
       references studijski_program (id);

    alter table katedra 
       add constraint FKjdhxd70cxg2hw10ovcyxq3vpc 
       foreign key (fakultet_id) 
       references fakultet (id);

    alter table katedra 
       add constraint FKglyyn2jxuebagnvkgn9e5p4jx 
       foreign key (sef_katedre_id) 
       references nastavnik (id);

    alter table mesto 
       add constraint FKs8rcbu6hgv8df9mee76jih079 
       foreign key (drzava_id) 
       references drzava (id);

    alter table nastavnik 
       add constraint FKh6fakdocaes8pn4aed9qh4g93 
       foreign key (osoba_id) 
       references osoba (id);

    alter table nastavnik_has_zvanje 
       add constraint FK6x5bgysjnwga4uo9n8fgr2tiy 
       foreign key (nastavnik_id) 
       references nastavnik (id);

    alter table nastavnik_has_zvanje 
       add constraint FKbr84rd4ql213lb0tsmfcqpnbb 
       foreign key (zvanje_id) 
       references zvanje (id);

    alter table obavestenje 
       add constraint FKlfjmq52o22q4p59to64aeq8jv 
       foreign key (forum_id) 
       references forum (id);

    alter table obavestenje 
       add constraint FKgncba7jwgdh9tgx3ypx2mx038 
       foreign key (korisnik_id) 
       references ulogovani_korisnik (id);

    alter table odgovor 
       add constraint FKtf1lutxx5c2tqhydmbehbubq9 
       foreign key (zadatak_id) 
       references zadatak (id);

    alter table osoba 
       add constraint FKpbamqwuop0vdtr1bb5mw2700v 
       foreign key (adresa_id) 
       references adresa (id);

    alter table pohadjanje_predmeta 
       add constraint FKskigfrejfgiob4xr8ai7w65xu 
       foreign key (realizacija_predmeta_id) 
       references realizacija_predmeta (id);

    alter table pohadjanje_predmeta 
       add constraint FKdbolvvj1fuj0powmeqr3v5xyu 
       foreign key (student_na_godini_id) 
       references student_na_godini (id);

    alter table polaganje 
       add constraint FKovo8yfdimt6l74kvcaih5l9q5 
       foreign key (evaluacija_znanja_id) 
       references evaluacija_znanja (id);

    alter table polaganje 
       add constraint FKbfiy5yvrcq5581atppi1th7c9 
       foreign key (rok_id) 
       references rok (id);

    alter table prijava_polaganja 
       add constraint FKoaa1jo3qfslmy5xjfgbxn3oip 
       foreign key (pohadjanje_predmeta_id) 
       references pohadjanje_predmeta (id);

    alter table prijava_polaganja 
       add constraint FK54cou4s3jy8ttcoisc4j77eq9 
       foreign key (polaganje_id) 
       references polaganje (id);

    alter table realizacija_predmeta 
       add constraint FKpij79o9rlod59kvoxw132u5vw 
       foreign key (godina_studija_id) 
       references godina_studija (id);

    alter table realizacija_predmeta 
       add constraint FKf6lwo1o12dk8cs431jvfylvg0 
       foreign key (nastavnik_id) 
       references nastavnik (id);

    alter table realizacija_predmeta 
       add constraint FKrw6tx0pbaevbvs89psuqfqijx 
       foreign key (predmet_id) 
       references predmet (id);

    alter table silabus 
       add constraint FK8n0hbugrw8n5tgbcwnfpm054n 
       foreign key (predmet_id) 
       references predmet (id);

    alter table student 
       add constraint FKywly8yb6ean4vgmmnil9obpa 
       foreign key (osoba_id) 
       references osoba (id);

    alter table student_na_godini 
       add constraint FKa0qj2cperexvshppcmuh1a1s7 
       foreign key (godina_studija_id) 
       references godina_studija (id);

    alter table student_na_godini 
       add constraint FKmu7rrfp6rrd3ds2i229h46d8l 
       foreign key (student_id) 
       references student (id);

    alter table studijski_program 
       add constraint FKgpym3o27qf0vmok93y3mhdrxn 
       foreign key (katedra_id) 
       references katedra (id);

    alter table termin 
       add constraint FKqu2p2pr010gsx7tb2ujv8942n 
       foreign key (realizacija_predmeta_id) 
       references realizacija_predmeta (id);

    alter table ulogovani_korisnik 
       add constraint FKc86q429ry6hnakc0i8akapgjp 
       foreign key (osoba_id) 
       references osoba (id);

    alter table univerzitet 
       add constraint FKofd922skj761vouuctldyyk3r 
       foreign key (adresa_id) 
       references adresa (id);

    alter table zadatak 
       add constraint FKfaun8yvwcab3lw4yp7pwtvssx 
       foreign key (evaluacija_znanja_id) 
       references evaluacija_znanja (id);

    alter table zvanje 
       add constraint FKl5hpqogqfdtev1q9rric2ifok 
       foreign key (naucna_oblast_id) 
       references naucna_oblast (id);

    alter table zvanje 
       add constraint FKkpu227epeh3exwr26w5ryql3m 
       foreign key (tip_zvanja_id) 
       references tip_zvanja (id);
