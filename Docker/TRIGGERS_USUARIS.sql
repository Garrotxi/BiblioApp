use BiblioApp;
delimiter #

create trigger T_USER_ROLE_ADD after insert on BiblioApp.Usuaris
for each row
begin
  insert into BiblioApp.ROLS_USUARI (ID_USUARI, ID_ROL) values (new.ID, new.ID_ROL);
end#

create trigger T_USER_ROLE_MOD after update on BiblioApp.Usuaris
for each row
begin
	if !(new.ID_ROL <=> old.ID_ROL) then
		update BiblioApp.ROLS_USUARI set ID_ROL = new.ID_ROL where ID = old.ID;
	end if;
end#
