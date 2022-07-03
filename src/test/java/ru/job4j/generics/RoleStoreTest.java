package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mage"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.add(new Role("1", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mage"));
    }

    @Test
    public void whenReplaceThenRoleNameIsPaladin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("1", new Role("1", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Paladin"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.replace("10", new Role("10", "Paladin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mage"));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenRoleNameIsMage() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mage"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Mage"));
    }
}