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

public final class LinkedList<E> {    
    private Node<E> First, Last;
    private int Length;
    
    public LinkedList() {
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
    
    public E Add(int Index, E Element) {
        if (Index > Length || Index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (Index == Length) {
            return this.Add(Element);
        } else if (Index == 0) {
            ++Length;
            First.Previous = new Node<>(Element);
            First.Previous.Next = First;
            First = First.Previous;
            return Element;
        }
        
        Node<E> Reference = First;
        
        for (int I = 0; I < Index - 1; ++I) {
            Reference = Reference.Next;
        }
        
        ++Length;
        Node<E> NewNode = new Node<>(Element);
        NewNode.Previous = Reference;
        NewNode.Next = Reference.Next;
        Reference.Next = NewNode;
        Reference.Next.Previous = NewNode;
        return Element;
    }
    
    public E Remove() {
        if (First == null) {
            throw new java.util.NoSuchElementException();
        } else if (Length == 1) {
            --Length;
            E Value = Last.Value;
            First = Last = null;
            return Value;
        }
        
        --Length;
        E Value = Last.Value;
        Last = Last.Previous;
        return Value;
    }
    
    public E Remove(int Index) {
        if (Index > Length || Index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (Index == Length - 1) {
            return this.Remove();
        } else if (Index == 0) {
            --Length;
            Node<E> Reference = First;
            First = First.Next;
            First.Previous = null;
            return Reference.Value;
        }
        
        Node<E> Reference = First;
        for (int I = 0; I < Index; ++I) {
            Reference = Reference.Next;
        }
        
        --Length;
        Reference.Previous.Next = Reference.Next;
        Reference.Next.Previous = Reference.Previous;
        return Reference.Value;
    }
    
    public void Clear() {
        First = null;
        Length = 0;
    }
    
    public E Get() {
        return this.Get(this.Length - 1);
    }
    
    public E Get(int Index) {
        if (First == null) {
            return null;
        }
        
        Node<E> Reference = First;
        for (int I = 0; I < Index; ++I) {
            if (Reference.Next == null) {
                return null;
            }     
            Reference = Reference.Next;
        }
        return Reference.Value;
    }
    
    public E GetValue() {
        return this.GetValue(this.Length - 1);
    }
    
    public E GetValue(int Index) {
        return this.Get(Index);
    }
    
    public int Length() {
        return this.Length;
    }
    
    public E[] toArray() {
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
    
    @Override public String toString() {
        if (First == null) {
            return null;
        }
        
        String Result = new String();
        for (int I = 0; I < Length - 1; ++I) {
            Result += String.valueOf(this.GetValue(I)) + ", ";
        }
        Result += String.valueOf(this.GetValue());
        return Result;
    }
}