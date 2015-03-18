/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xwizard.karteczki.events;

public interface Handler<T extends Event> {
  void handle(T event);
  boolean handles(Class<T> aClass);
}
