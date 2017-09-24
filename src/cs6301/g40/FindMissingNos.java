package cs6301.g40;

import java.util.ArrayList;
import java.util.Scanner;

public class FindMissingNos
{
    int [] numbers;
    //int max;
    //int min;
    int sizeOfNos;

    public FindMissingNos(int [] N,int size)
    {
        numbers = new int[size];
        numbers = N;
        sizeOfNos=size;
        //max= size-1;
        //min = 0;
    }

    public FindMissingNos(FindMissingNos obj,int Min, int Max)
    {
        int index=0;
        this.numbers = new int[Max-Min+1];
        if(Min < Max)
            for(int i = Min; i <= Max; i++)
                this.numbers[index++] = obj.numbers[i];
        else
        {
            Min = Max;
            this.numbers[index++] = obj.numbers[Max];
        }

        this.sizeOfNos=Max-Min + 1;
        //this.max= obj.max;
        //this.min = obj.min;
    }

    public ArrayList<Integer> findMissingNos( ArrayList<Integer> outputNos, ArrayList<Integer> left, ArrayList<Integer> right, int expecLow, int expecHigh)
    {
        int lowMax,highMin=0,highMax=0;
            if(expecLow != -1)
            {
                for (int i = expecLow; i != this.numbers[0]; i++)
                {
                    outputNos.add(i);
                }
            }
            if(expecHigh != -1)
            {
                for (int i = expecHigh; i != this.numbers[this.sizeOfNos-1]; i--)
                {
                    outputNos.add(i);
                }
            }
        if((this.numbers[this.sizeOfNos-1]-this.numbers[0] + 1) != this.sizeOfNos)
        {
            if(this.sizeOfNos < 6)
            {
                int counter = 0;
                /*
                for (int i = expecLow; i <this.numbers[0]; i++ )
                    outputNos.add(i);
                for (int i = this.numbers[this.sizeOfNos-1]; i <expecHigh; i++ )
                    outputNos.add(i);
                    */
                while(counter!=(this.sizeOfNos-1))
                {
                    for (int i = this.numbers[counter]+1; i <this.numbers[counter+1]; i++ )
                        outputNos.add(i);
                    counter++;
                }
                return outputNos;
            }
            if(this.sizeOfNos >= 6)
            {
                FindMissingNos Low = new FindMissingNos(this,1,(this.sizeOfNos/2)-1);
                highMax = this.sizeOfNos-2;
                highMin = this.sizeOfNos - (this.sizeOfNos/2);
                if(highMax < highMin)
                {
                    highMin = highMax;
                    //highMax = highMin;
                }
                FindMissingNos High = new FindMissingNos(this,highMin,highMax);
               /*
                Low.min=this.min+1;
                Low.max=this.sizeOfNos/2;
                Low.sizeOfNos=Low.max-Low.min + 1;
                High.min=this.sizeOfNos - Low.max;
                High.max = this.max - 1;
                High.sizeOfNos=High.max-High.min + 1;
                */
                lowMax = (this.sizeOfNos/2) - 1;
                left.clear();
                right.clear();
                 Low.findMissingNos(outputNos,left,right,this.numbers[0]+1,-1);
                //outputNos.addAll(left);
                if(lowMax!=highMin)
                {
                    High.findMissingNos(outputNos,left,right,-1,this.numbers[this.sizeOfNos-1]-1);
                    //outputNos.addAll(right);
                }
                highMin = lowMax + 1;
                for(int i = this.numbers[lowMax]+1; i != this.numbers[highMin]; i++)
                {
                    outputNos.add(i);
                }
            }
        }

        return outputNos;
    }

    public static void main(String[] args)
    {
        int max=0,n=0;
        ArrayList<Integer> output = new ArrayList<>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter max number the array can store");
        max=in.nextInt();

        System.out.println("Enter size of array");
        n=in.nextInt();

        int[] num = new int[n];
        System.out.println("Enter sorted array if size " + n);
        for (int i = 0; i < n; i++)
            num[i]=in.nextInt();

        FindMissingNos k = new FindMissingNos(num,n);
        output = k.findMissingNos(output,left,right,1,max);

        System.out.println("Missing numbers " + output.size() + output);
    }
}
