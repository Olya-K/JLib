/**  © 2014, Olga K. All Rights Reserved.
  *
  *  This file is part of the JLib Library.
  *  JLib is free software: you can redistribute it and/or modify
  *  it under the terms of the GNU General Public License as published by
  *  the Free Software Foundation, either version 3 of the License, or
  *  (at your option) any later version.
  *
  *  JLib is distributed in the hope that it will be useful,
  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  *  GNU General Public License for more details.
  *
  *  You should have received a copy of the GNU General Public License
  *  along with JLib.  If not, see <http://www.gnu.org/licenses/>.
  */

package jlib;

public final class Queue<E> {
    private int Length;
    private Node<E> First, Last;
    
    public Queue() {
        First = null;
        Length = 0;
    }
    
    public E Add(E Element) {
        if (First == null) {
            ++Length;
            First = Last = new Node<>(Element);
            return Element;
        }
        
        ++Length;
        Last.Next = new Node<>(Element);
        Last.Next.Previous = Last;
        Last = Last.Next;
        return Element;
    }
    
    public E Remove() {
        if (First == null) {
            throw new java.util.EmptyStackException();
        } else if (Length == 1) {
            --Length;
            E Value = Last.Value;
            First = Last = null;
            return Value;
        }
        
        First = First.Next;
        Node<E> Reference = First.Previous;
        First.Previous = null;
        --Length;
        return Reference.Value;
    }
    
    public E Front() {  
        return (First == null ? null : First.Value);
    }
    
    public E Back() {
        if (First == null) {
            return null;
        }
        
        return Last.Value;
    }
    
    public boolean Empty() {
        return (Length == 0);
    }
    
    public void Clear() {
        First = null;
        Length = 0;
    }
    
    public int Size() {
        return Length;
    }
    
    public E[] ToArray() {
        if (First == null) {
            return null;
        }
        
        Node<E> Reference = First;
        E[] Result = (E[])java.lang.reflect.Array.newInstance(First.Value.getClass(), Length);
        
        for (int I = 0; Reference != null; ++I) {
            Result[I] = Reference.Value;
            Reference = Reference.Next;
        }
        
        return Result;
    }
}