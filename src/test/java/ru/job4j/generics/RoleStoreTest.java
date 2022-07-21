package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Mage");
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.add(new Role("1", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Mage");
    }

    @Test
    public void whenReplaceThenRoleNameIsPaladin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("1", new Role("1", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Paladin");
    }

    @Test
    public void whenNoReplaceUserThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("10", new Role("10", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Mage");
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    public void whenNoDeleteUserThenRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Mage");
    }
}