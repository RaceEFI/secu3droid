package org.secu3.android.api.io;

public class EditTabPar extends Secu3Dat {
	public int tab_set_index;          //����� ������ ������
	public int tab_id;                 //������������� �������(������) � ������
	public int address;                 //����� ������ ��������� ������ � �������
	public float table_data[];                 //�������� ������ (�� ����� 16-�� ����)
	public int name_data[]; 
	public int data_size;               //������ ��������� ������
  
	public EditTabPar() {
		table_data = new float[32];
		name_data = new int[32];
	}
}
