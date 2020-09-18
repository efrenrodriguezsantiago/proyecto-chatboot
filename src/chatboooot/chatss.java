package chatboooot;


	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JTextArea;
	import javax.swing.JScrollPane;

	import java.awt.Color;

	import java.awt.event.KeyListener;
	import java.awt.event.KeyEvent;

	import java.lang.Math;

	public class chatss extends JFrame implements KeyListener{

		JPanel p=new JPanel();
		JTextArea dialog=new JTextArea(20,50);
		JTextArea input=new JTextArea(1,50);
		JScrollPane scroll=new JScrollPane(
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		
		String[][] chatBot={
			//saludos estándar
			{"Hola","hola","hola","hola","hola"},
			{"hola","hola","hola"},
			//pregunta saludos
			{"como estas","estas bien","todo bien","..."},
			{"muy bien. en que puedo ayudarte?"},
			//si
			{"solicito soporte"},
			{"no","NO","NO!!!!!!!"},
			//default
			{"no disponible hasta tarde",
			"(cuevas no está disponible, debido a saturacion de servicio)"}
		};
		
		public static void main(String[] args){
			new chatss();
		}
		
		public chatss(){
			super("Chat Bot");
			setSize(600,400);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			dialog.setEditable(false);
			input.addKeyListener(this);
		
			p.add(scroll);
			p.add(input);
			p.setBackground(new Color(255,200,0));
			add(p);
			
			setVisible(true);
		}
		
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				input.setEditable(false);
				//-----agarrar cita-----------
				String quote=input.getText();
				input.setText("");
				addText("-->tu:\t"+quote);
				quote.trim();
				while(
					quote.charAt(quote.length()-1)=='!' ||
					quote.charAt(quote.length()-1)=='.' ||
					quote.charAt(quote.length()-1)=='?'
				){
					quote=quote.substring(0,quote.length()-1);
				}
				quote.trim();
				byte response=0;
				/*
				0: buscamos coincidencias en chatBot [] []
				1: no encontramos nada
				2: encontramos algo
				*/
				//-----buscar coincidencias----
				int j=0;//qué grupo estamos revisando
				while(response==0){
					if(inArray(quote.toLowerCase(),chatBot[j*2])){
						response=2;
						int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
						addText("\n-->cuevas\t"+chatBot[(j*2)+1][r]);
					}
					j++;
					if(j*2==chatBot.length-1 && response==0){
						response=1;
					}
				}
				
				//-----default--------------
				if(response==1){
					int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
					addText("\n-->cuevas\t"+chatBot[chatBot.length-1][r]);
				}
				addText("\n");
			}
		}
		
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				input.setEditable(true);
			}
		}
		
		public void keyTyped(KeyEvent e){}
		
		public void addText(String str){
			dialog.setText(dialog.getText()+str);
		}
		
		public boolean inArray(String in,String[] str){
			boolean match=false;
			for(int i=0;i<str.length;i++){
				if(str[i].equals(in)){
					match=true;
				}
			}
			return match;
		}
	}

