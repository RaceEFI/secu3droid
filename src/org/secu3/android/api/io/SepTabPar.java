package org.secu3.android.api.io;

public class SepTabPar {
	public int address;                 //����� ������ ��������� ������ � �������
	public float table_data[];                 //�������� ������ (�� ����� 16-�� ����)
	public int data_size;               //������ ��������� ������
  
	public SepTabPar()
	{
		table_data = new float[32];
	}
}
