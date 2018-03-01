package testKmeans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class kMeans {

	final static int K=3;
	final static int INDEX=0;
	final static int CAREER=1;
	final static int TIME=2;
	static int iteration=1;

	public static void main(String[] args) throws Exception {

		// 데이터 파일을 출력한다.
		String str;
		BufferedReader in=new BufferedReader(new FileReader("data.txt"));
		ArrayList list=new ArrayList();
		int index = 0;
		while((str=in.readLine())!=null){
			index++;
			list.add(str);
		}
		in.close();

		printData[] dataset=new printData[index];
		for(int i=0; i<index; i++){
			String tok=(String)list.get(i);
			String[] token=tok.split(" ");
			dataset[i]=new printData(Integer.parseInt(token[INDEX]), Double.parseDouble(token[CAREER]), Double.parseDouble(token[TIME]));
		}
		System.out.println("**********Data**********");
		for(int i=0; i<index; i++){
			System.out.println(dataset[i]);
		}

		//임의의 좌표 선택
//		Random random = new Random();
//		int randomSelect[]=new int[K];
//		for(int i=0; i<K; i++){
//			randomSelect[i] = random.nextInt(index);
//			for(int j=0; j<i; j++){
//				if(randomSelect[i]==randomSelect[j]){
//					i=i-1;
//					break;
//				}
//			}
//		}
		
		//test
		int randomSelect[] = {3,4,5};

		ArrayList<printData> centroid = new ArrayList<>();
		for(int n : randomSelect){
			centroid.add(dataset[n]);
		}

		boolean finish=false;
		while(!finish){		//while문 시작

			System.out.println();
			System.out.println();
			System.out.println("Iteration " + iteration);
			System.out.println("**********Centroid**********");
			for(printData data : centroid){
				System.out.println(data);
			}

			//거리계산
			printDistance distance[]=new printDistance[dataset.length];
			for(int i=0; i<dataset.length; i++){
				double c1x = Math.pow(centroid.get(0).getCareer()-dataset[i].getCareer(), 2);
				double c1y = Math.pow(centroid.get(0).getTime()-dataset[i].getTime(), 2);
				double c2x = Math.pow(centroid.get(1).getCareer()-dataset[i].getCareer(), 2);
				double c2y = Math.pow(centroid.get(1).getTime()-dataset[i].getTime(), 2);
				double c3x = Math.pow(centroid.get(2).getCareer()-dataset[i].getCareer(), 2);
				double c3y = Math.pow(centroid.get(2).getTime()-dataset[i].getTime(), 2);
				distance[i] = new printDistance(Double.parseDouble(String.format("%.2f", Math.sqrt(c1x+c1y))),
						Double.parseDouble(String.format("%.2f", Math.sqrt(c2x+c2y))),
						Double.parseDouble(String.format("%.2f", Math.sqrt(c3x+c3y))));
			}

			//거리 출력
			System.out.println();
			System.out.println("**********Distance**********");
			System.out.println("     c1    c2    c3");
			int number2=1;
			for(int i=0; i<dataset.length; i++){
				System.out.println(number2+" "+distance[i]);
				number2++;
			}

			//최소거리 구하기
			ArrayList<printData> c1 = new ArrayList<>();
			ArrayList<printData> c2 = new ArrayList<>();
			ArrayList<printData> c3 = new ArrayList<>();

			ArrayList<Double> distanceCompare = new ArrayList<Double>();
			for(int i=0; i<distance.length;i++){
				distanceCompare.add(distance[i].getCentroidDistance1());
				distanceCompare.add(distance[i].getCentroidDistance2());
				distanceCompare.add(distance[i].getCentroidDistance3());
				double min = Collections.min(distanceCompare);

				if(min==distance[i].getCentroidDistance1())
					c1.add(dataset[i]);
				else if(min==distance[i].getCentroidDistance2())
					c2.add(dataset[i]);
				else if(min==distance[i].getCentroidDistance3())
					c3.add(dataset[i]);

				distanceCompare.removeAll(distanceCompare);
			}

			//새로운 좌표설정
			ArrayList<printData> newCentroid = new ArrayList<>();
			//c1 재설정
			double sumC1x=0;
			double sumC1y=0;
			double newC1x=0;
			double newC1y=0;
			int c1Index=0;

			for(int i=0; i<c1.size(); i++){
				sumC1x += c1.get(i).getCareer();
				newC1x=sumC1x/c1.size();
				sumC1y += c1.get(i).getTime();
				newC1y=sumC1y/c1.size();
				c1Index=c1.get(i).getIndex();
			}
			newCentroid.add(new printData(c1Index, Double.parseDouble(String.format("%.2f", newC1x)),
					Double.parseDouble(String.format("%.2f", newC1y))));

			//c2 재설정
			double sumC2x=0;
			double sumC2y=0;
			double newC2x=0;
			double newC2y=0;
			int c2Index=0;
			for(int i=0; i<c2.size(); i++){
				sumC2x += c2.get(i).getCareer();
				newC2x=sumC2x/c2.size();
				sumC2y += c2.get(i).getTime();
				newC2y=sumC2y/c2.size();
				c2Index=c2.get(i).getIndex();
			}
			newCentroid.add(new printData(c2Index, Double.parseDouble(String.format("%.2f", newC2x)),
					Double.parseDouble(String.format("%.2f", newC2y))));

			//c3 재설정
			double sumC3x=0;
			double sumC3y=0;
			double newC3x=0;
			double newC3y=0;
			int c3Index=0;
			for(int i=0; i<c3.size(); i++){
				sumC3x += c3.get(i).getCareer();
				newC3x=sumC3x/c3.size();
				sumC3y += c3.get(i).getTime();
				newC3y=sumC3y/c3.size();
				c3Index=c3.get(i).getIndex();
			}
			newCentroid.add(new printData(c3Index, Double.parseDouble(String.format("%.2f", newC3x)),
					Double.parseDouble(String.format("%.2f", newC3y))));

			//새로운 중심좌표 출력
			System.out.println();
			System.out.println("**********New Centroid**********");
			for(printData data : newCentroid){
				System.out.println(data);
			}

			//이전 중심좌표와 새로운 중심좌표와 비교
			double changeDistance=0;
			for(int i=0; i<K; i++){
				changeDistance = changeDistance + Math.sqrt(Math.pow(centroid.get(i).getCareer()-newCentroid.get(i).getCareer(), 2));
				changeDistance = changeDistance + Math.sqrt(Math.pow(centroid.get(i).getTime()-newCentroid.get(i).getTime(), 2));
			}

				if(changeDistance==0){
					finish=true;

					//현재 군집 상태 출력
					System.out.println();
					System.out.println();
					System.out.println("K-means clustering with clustering of sizes " + c1.size() +", "+ c2.size() +", "+ c3.size());
					System.out.println("**********Cluster Result**********");
					System.out.print("Cluster 1 = {");
					for(int i=0; i<c1.size(); i++)
						System.out.print(" "+c1.get(i).getIndex()+" ");
					System.out.println("}");

					System.out.print("Cluster 2 = {");
					for(int i=0; i<c2.size(); i++)
						System.out.print(" "+c2.get(i).getIndex()+" ");
					System.out.println("}");

					System.out.print("Cluster 3 = {");
					for(int i=0; i<c3.size(); i++)
						System.out.print(" "+c3.get(i).getIndex()+" ");
					System.out.println("}");
				}
				else
					for(int i=0; i<centroid.size(); i++){
						centroid.set(i, newCentroid.get(i));
					}
			iteration++;
		}//while문 끝

	}

}