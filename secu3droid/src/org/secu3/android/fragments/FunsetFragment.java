package org.secu3.android.fragments;

import java.util.Arrays;

import org.secu3.android.R;
import org.secu3.android.api.io.Secu3Dat;
import org.secu3.android.api.io.Secu3Dat.FnNameDat;
import org.secu3.android.api.io.Secu3Dat.FunSetPar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FunsetFragment extends Fragment implements ISecu3Fragment{
	FunSetPar packet;
	FnNameDat extraPacket;
	
	EditText lowerPressure;
	EditText upperPressure;
	EditText sensorOffset;
	EditText sensorGradient;
	Spinner gasolineTable;
	Spinner gasTable;
	String tableNames[] = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) return null;
			
		return inflater.inflate(R.layout.funset_params, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lowerPressure = (EditText)getView().findViewById(R.id.funsetLowerPressureEditText);
		upperPressure = (EditText)getView().findViewById(R.id.funsetUpperPressureEditText);
		sensorOffset = (EditText)getView().findViewById(R.id.funsetMAPSensorOffsetEditText);
		sensorGradient = (EditText)getView().findViewById(R.id.funsetMAPSensorGradientEditText);
		gasolineTable = (Spinner)getView().findViewById(R.id.funsetGasolineTableSpinner);
		gasTable  = (Spinner)getView().findViewById(R.id.funsetGasTableSpinner);
		gasolineTable = (Spinner)getView().findViewById(R.id.funsetGasolineTableSpinner);
		gasTable = (Spinner)getView().findViewById(R.id.funsetGasTableSpinner);		
	}
	
	@Override
	public void onResume() {
		updateData();		
		super.onResume();
	}

	@Override
	public void updateData() {
		if (packet != null) {				
			lowerPressure.setText (String.format("%.2f",packet.map_lower_pressure));
			upperPressure.setText (String.format("%.2f",packet.map_upper_pressure));
			sensorOffset.setText (String.format("%.2f",packet.map_curve_offset));
			sensorGradient.setText (String.format("%.2f",packet.map_curve_gradient));
			gasolineTable.setSelection(packet.fn_benzin);
			gasTable.setSelection(packet.fn_gas);
		}
		if ((extraPacket != null) && (extraPacket.names_available())) {					
			tableNames = Arrays.copyOf(extraPacket.names,extraPacket.names.length);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity().getBaseContext(), android.R.layout.simple_spinner_item,tableNames);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			gasolineTable.setAdapter(adapter);
			
			adapter = new ArrayAdapter<String>(this.getActivity().getBaseContext(), android.R.layout.simple_spinner_item,tableNames);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			gasTable.setAdapter(adapter);
			
			if (packet != null) {
				gasolineTable.setSelection(packet.fn_benzin);
				gasTable.setSelection(packet.fn_gas);				
			}
		}
	}

	@Override
	public void setData(Secu3Dat packet) {
		if (packet instanceof FunSetPar) {
			this.packet = (FunSetPar) packet;
		}
		if (packet instanceof FnNameDat) {
			this.extraPacket = (FnNameDat) packet;
		}
	}

	@Override
	public Secu3Dat getData() {
		if (packet == null) return null;
		packet.map_lower_pressure = Float.valueOf(lowerPressure.getText().toString());
		packet.map_upper_pressure = Float.valueOf(upperPressure.getText().toString());
		packet.map_curve_offset = Float.valueOf(sensorOffset.getText().toString());
		packet.map_curve_gradient = Float.valueOf(sensorGradient.getText().toString());
		packet.fn_benzin = gasolineTable.getSelectedItemPosition();
		packet.fn_gas = gasTable.getSelectedItemPosition();
		return packet;
	}
	
	public Secu3Dat getExtraData() {
		return extraPacket;
	}
}
