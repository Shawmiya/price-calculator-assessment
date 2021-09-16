import { Component, OnInit, TemplateRef } from '@angular/core';
import { Options } from '@angular-slider/ngx-slider';
import { ApiService } from '../service/api.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

export class Products {
  constructor(
    public productName: string,
    public price: any
  ) {}
}

export class Orders {
  constructor(
    public id: number,
    public name: string,
    public type: string,
    public count: string
  ) {}
}

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})

export class PricelistComponent implements OnInit {
  noOfProducts: any;
  pricelist!: Products[];
  value: any = [];
  options: Options = {
    floor: 1,
    ceil: 50
  };
  itemList:Orders[] = [];
  total: any = 0.00;

  constructor(private service:ApiService, private ngModalService: NgbModal) { }

  ngOnInit(): void {
    this.showList();
  }

  showList() {
    this.service.getPriceList()
      .subscribe(data => {
        this.pricelist = data;
        this.noOfProducts = data.length;
        for (let index = 0; index < this.noOfProducts; index++) {
          this.value[index]=1;
        }
      });
  }

  addTask(prod:string,unitType:string,count:string) {
    this.itemList.push({id:this.itemList.length, name:prod, type:unitType, count:count});
  }

  removeTask(id:number) {
    this.itemList=this.itemList.filter(item=>item.id!==id);
  }

  removeAllTasks() {
    this.itemList = [];
    this.total = 0.00;
  }

  openLg(content: any) {
    this.ngModalService.open(content, { size: 'lg' });
  }

  calculateThePrice() {
    this.service.getCalculatedPrice(this.itemList)
      .subscribe(data => {
        this.total = data;
      });
  }
}
