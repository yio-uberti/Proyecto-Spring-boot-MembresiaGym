
create table profesor(
	id_profesor int auto_increment primary key,
    nombre_profesor varchar(50),
    apellido_profesor varchar(50),
    DNI_profesor int (8) not null
);

create table asistencia(
	id_asistencia int auto_increment primary key,
    descripcion_asistencia varchar(50)
);

create table actividad(
	id_act int auto_increment primary key,
    descripcion_act varchar(50),
    id_profesor_act int not null,
    
    foreign key (id_profesor_act) references profesor(id_profesor)
);

create table alumnos(
	id_alumnos int auto_increment primary key,
    nombre_alumno varchar(50),
    apellido_alumno varchar(50),
    DNI_alumno int(8) not null,
    tipo_asistencia int not null,
    foreign key (tipo_asistencia) references asistencia(id_asistencia)
);

create table clases(
	id int auto_increment primary key,
    id_alumnos int not null,
    id_actividad int not null,
    dia varchar(50) not null,
    horario_alumno time not null,

    foreign key (id_alumnos) references alumnos(id_alumnos),
    foreign key (id_actividad) references actividad(id_act),
    CHECK (horario_alumno >= '07:00:00' AND horario_alumno <= '21:00:00')
);

create table rol(
	id_rol bigint auto_increment primary key,
    rol_nombre varchar(50) not null
);

create table usuario(
	id_usuario bigint auto_increment primary key,
    username varchar(50) not null unique,
    `password` varchar(100) not null
);

create table usuario_roles(
	id_usuario bigint not null,
    id_rol bigint not null,
    
    primary key (id_usuario, id_rol),
    foreign key(id_usuario) references usuario(id_usuario),
    foreign key(id_rol) references rol(id_rol)
);